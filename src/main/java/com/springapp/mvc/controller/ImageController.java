package com.springapp.mvc.controller;

/*
 *Created By Intellij IDEA
 *User:Jade.Xiao
 *Date:2018/7/16
 *Time:11:32
 */


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.util.Map;


@Controller
public class ImageController {
    @RequestMapping(value = "ImageIndex")
    public ModelAndView index(){
        return new ModelAndView("test/Image");
    }



    @ResponseBody
    @RequestMapping(value = "base64" , method = RequestMethod.POST)
    public String baseToPdf(@RequestBody Map<String,Object> params){
        String pdfBase64Str=params.get("pdfBase64Str").toString();
       String  filepath="C:\\Users\\Jade.xiao\\Desktop\\test.pdf";
            BufferedInputStream bis = null;
            FileOutputStream fos = null;
            BufferedOutputStream bos = null;
            try{
                BASE64Decoder decoder=new BASE64Decoder();
                byte[] bytes=decoder.decodeBuffer(pdfBase64Str);
                ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(bytes);
                bis=new BufferedInputStream(byteArrayInputStream);
                File file=new File(filepath);
                File path=file.getParentFile();
                if(!path.exists()){
                    path.mkdirs();
                }
                fos=new FileOutputStream(file);
                bos=new BufferedOutputStream(fos);

                byte[] buffer=new byte[1024];
                int length=bis.read(buffer);
                while(length!=-1){
                    bos.write(buffer,0,length);
                    length=bis.read(buffer);
                }
                bos.flush();
            }catch(Exception e){
                e.printStackTrace();
            }finally {
                try{
                    bis.close();
                    bos.close();
                    fos.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

return "1";
        }

}
