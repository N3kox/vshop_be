package com.myshop.prototype.controller;

import com.myshop.prototype.bean.Item;
import com.myshop.prototype.impl.ItemImpl;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.SocketException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/item")
public class ItemController extends ItemImpl {
    Integer UNVERIFIED = Integer.valueOf(0);    //未审查
    Integer VERIFIED = Integer.valueOf(1);      //已审查

    //获取全部item
    @GetMapping("/all")
    @ResponseBody
    public List<Item> getAllItemInfo(){
        return selectAll();
    }

    //审批
    @PutMapping("/confirm")
    @ResponseBody
    public Item verifyConfirm(@Param("iid") Long iid){
        Item ni = getItemInfo(iid);
        ni.setVerify(VERIFIED);
        return updateItem(ni);
    }

    //下架
    @PutMapping("/refuse")
    @ResponseBody
    public Item verifyRefuse(@Param("iid") Long iid){
        Item ni = getItemInfo(iid);
        ni.setVerify(UNVERIFIED);
        return updateItem(ni);
    }


    //获取已审查items
    @GetMapping("/verified")
    @ResponseBody
    public List<Item> getVerifiedItems(){
        return getByVerify(VERIFIED);
    }

    //获取未审查items
    @GetMapping("/unverified")
    @ResponseBody
    public List<Item> getUnverifiedItems(){
        return getByVerify(UNVERIFIED);
    }

    //加入新商品
    @PostMapping("/insert")
    @ResponseBody
    public Item addItemInfo(@Param("uid") Long uid,
                            @Param("name") String name,
                            @Param("price") Integer price,
                            @Param("quantity") Integer quantity,
                            @Param("description") String description){
        Item ni = new Item();
        if(uid == null || name == null || price == null)
            return null;
        ni.setUid(uid);
        ni.setName(name);
        ni.setPrice(price);
        ni.setQuantity(quantity == null ? 0 : quantity);
        ni.setDescription(description == null ? "Default" : description);
        ni.setVerify(UNVERIFIED);
        return insertItem(ni);
    }

    @GetMapping("/get")
    @ResponseBody
    public Item getItemInfo(@Param("iid") Long iid){
        Optional<Item> item = selectItem(iid);
        return item.orElse(null);
    }


    //加入新商品
    @PutMapping("/update")
    @ResponseBody
    public Item updateItemInfo(@Param("iid")Long iid,
                               @Param("name") String name,
                               @Param("price") Integer price,
                               @Param("quantity") Integer quantity,
                               @Param("description") String description){
        if(iid == null) return null;
        Item ni = getItemInfo(iid);
        if(ni == null) return null;
        if(name != null) ni.setName(name);
        if(price != null) ni.setPrice(price);
        if(quantity != null) ni.setQuantity(quantity);
        if(description != null) ni.setDescription(description);
        ni.setVerify(UNVERIFIED);
        return insertItem(ni);
    }

    //删除商品
    @DeleteMapping("/delete")
    @ResponseBody
    public String deleteItemInfo(@Param("iid") Long iid){
        deleteItem(iid);
        return "ok";
    }

    //订购商品
    @PutMapping("/order")
    @ResponseBody
    public String orderItem(@Param("iid") Long iid, @Param("uid") Long uid, @Param("quantity") Integer quantity){
        if(iid == null || uid == null || quantity == null)
            return ItemControllerError("null param(s)");
        //注意quantity二次检查防止异步刷新导致购买数量出错
        Item ni = getItemInfo(iid);
        if(ni.getQuantity() < quantity)
            return ItemControllerError("stock less than demand");
        // TODO:订购修改Item与Cart
        return "ok";
    }

    //立即购买
    @PutMapping("buynow")
    @ResponseBody
    public String buyItemNow(@Param("iid") Long iid, @Param("uid") Long uid, @Param("quantity") Integer quantity){
        if(iid == null || uid == null || quantity == null)
            return ItemControllerError("null param(s)");
        //注意quantity二次检查防止异步刷新导致购买数量出错
        Item ni = getItemInfo(iid);
        if(ni.getQuantity() < quantity)
            return ItemControllerError("stock less than demand");
        // TODO:购买修改Item与Cart
        return "ok";
    }



    //print error
    public String ItemControllerError(String cause){
        return "ItemControllerError : "+cause;
    }
    @PostMapping("/upload/image")
    @ResponseBody
    public String uploadItemImage(@RequestParam Map<String, String> map, MultipartFile file) throws SocketException, IOException{
        if(map.size() == 0) {
            System.out.println("#Error : no param setting");
            return null;
        }

        Long iid = Long.valueOf(map.get("iid").toString());
        /*
        for(String key : map.keySet()){
            System.out.println("Key : "+key+"\tValue : "+map.get(key));
        }
         */

        String path = ResourceUtils.getURL("classpath:").getPath();
        if(file != null){
            String newName = iid+".jpg";
            System.out.println(path);
            File uploadFile = new File(path+"/static/"+newName);
            file.transferTo(uploadFile);
            System.out.println("goodUpload");
            return "goodUpload";
        }else{
            System.out.println("MultipartFile 输入错误！！");
            System.out.println("badUpload");
            return "badUpload";
        }
        /*
        OutputStreamWriter op = new OutputStreamWriter(new FileOutputStream("/static/"+iid+".jpg"), "UTF-8");
        InputStreamReader inputStreamReader = new InputStreamReader(file.getInputStream());
        char[] bytes = new char[256];
        while(inputStreamReader.read(bytes) != -1){
            op.write(bytes);
        }
        op.close();
        inputStreamReader.close();
        System.out.println("write file finished!");
        return "imageUploadDone"
         */
    }

}
