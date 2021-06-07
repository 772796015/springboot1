package com.example.demo.example.FileUpload;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *文件上传和下载
 *
 * Created by DJ010199 on 2020/7/1.
 */
@Controller
public class FileUploadController {
    private  static Map<String,Object> prossG=new HashMap<String,Object>();
    /*
     * 获取file.html页面   访问地址http://localhost:8082/file
     */
    @RequestMapping("file")
    public String file(){
        return "/file";
    }

    /**
     * 实现文件上传  注意：可以用谷歌浏览器，不可以360，因为360的话 file.getOriginalFilename();获取的为全路径不是文件名
     * */
    @RequestMapping("fileUpload")
    @ResponseBody
    public String fileUpload(@RequestParam("fileName") MultipartFile file){
        if(file.isEmpty()){
            return "false";
        }
        // 获取原始名字
        String fileName = file.getOriginalFilename();
        //获取文件大小
        int size = (int) file.getSize();
        System.out.println(fileName + "-->" + size);
        // 文件保存路径
        String path="D:/test1";
        // 文件对象
        File dest = new File(path + "/" + fileName);
        if(!dest.getParentFile().exists()){ //判断文件父目录是否存在，如果不存在则创建
            dest.getParentFile().mkdir();
        }
        try {
            // 保存到服务器中
            file.transferTo(dest);
            return "true";
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "false";
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "false";
        }
    }

/*
     * 获取multifile.html页面   访问地址http://localhost:8082/multifile
     */
    @RequestMapping("multifile")
    public String multifile(){
        return "/multifile";
    }

    /**
     * 实现多文件上传
     * */
    @RequestMapping(value="multifileUpload",method= RequestMethod.POST)
     /**public @ResponseBody String multifileUpload(@RequestParam("fileName")List<MultipartFile> files) */
    public @ResponseBody String multifileUpload(HttpServletRequest request){

        List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("fileName");

        if(files.isEmpty()){
            return "false";
        }

        // 文件保存路径
        String path="D:/test1";

        for(MultipartFile file:files){
            // 获取原始名字
            String fileName = file.getOriginalFilename();
            //获取文件大小
            int size = (int) file.getSize();
            System.out.println(fileName + "-->" + size);

            if(file.isEmpty()){
                return "false";
            }else{
                File dest = new File(path + "/" + fileName);
                if(!dest.getParentFile().exists()){ //判断文件父目录是否存在，如果不存在则创建
                    dest.getParentFile().mkdir();
                }
                try {
                    // 保存到服务器中
                    file.transferTo(dest);
                }catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    return "false";
                }
            }
        }
        return "true";
    }




    //文件下载  直接访问http://localhost:8082/download   就可以下载
    @RequestMapping("/download")
    public String downLoad(HttpServletResponse response) throws UnsupportedEncodingException {
        String filename="111.txt";
        String filePath = "D:/test1" ;
        File file = new File(filePath + "/" + filename);
        if(file.exists()){ //判断文件父目录是否存在
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            // response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;fileName=" +   java.net.URLEncoder.encode(filename,"UTF-8"));
            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;

            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while(i != -1){
                    os.write(buffer);
                    i = bis.read(buffer);
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("----------file download---" + filename);
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }


    /**********************上传显示进度条演示*****************************************************************************************************************************************************************/
    /*
     * fileUpload.html页面   访问地址http://localhost:8082/fileUpload
     */
    @RequestMapping("/fileUpload")
    public String fileUpload(){
        return "/fileUpload";
    }

    @RequestMapping("/uploadFile")
    @ResponseBody
    public String uploadFile(MultipartFile file) {

        String orgName = file.getOriginalFilename();

        try {
            //写入文件长度和初始进度
            prossG.put(orgName + "Size", file.getSize());
            //文件进度长度
            long progress = 0;
            //用流的方式读取文件，以便可以实时的获取进度
            InputStream in = file.getInputStream();
            File targetFile = new File("D:/test/"+ orgName);
            targetFile.createNewFile();
            FileOutputStream out = new FileOutputStream(targetFile);
            byte[] buffer = new byte[1024];
            int readNumber = 0;
            while ((readNumber = in.read(buffer)) != -1) {//此处进度条精华在此--------取上传的大小，然后除总大小
                //每读取一次，更新一次进度大小
                progress = progress + readNumber;
                //写入进度
                prossG.put(orgName + "Progress", progress);
                System.out.println("上传过程中"+progress);
                out.write(buffer);
            }
            //当文件上传完成之后，移除此次上传的状态信息
            prossG.remove(orgName + "Size");
            prossG.remove(orgName + "Progress");
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }




    @RequestMapping("/ProgressFile")
    @ResponseBody
    public Map<String,Object> ProgressFile(MultipartFile file) {
        String orgName = file.getOriginalFilename();
        Object size = prossG.get(orgName + "Size");
        size = size == null ? 100 : size;
        Object progress = prossG.get(orgName + "Progress");
        System.out.println("刷新过程中"+progress);
        progress = progress == null ? 0 : progress;
        Map<String,Object> json = new HashMap<>();
        json.put("size", size);
        json.put("progress", progress);
        return json;
    }
}

