package stock;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeMap;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class Get四季_以前的每年的四个季度的利润 {
  
  private static Integer stockNum = 300001;   //0开头的要以9开
  private static String flag = "2";  //1 全部   2 行业  3个股 
  
  
  private static List<String> listUrl = new ArrayList<String>();
  
  @SuppressWarnings("unused")
  public static void main(String [] args) throws Exception {

                                 
    

        List<Integer> prepareData = Get四季_以前的每年的四个季度的利润.prepareData();
        
        
        
        
        List<LinkedHashMap<String,List<TreeMap<String,Object>>>> list行业 =new ArrayList<LinkedHashMap<String,List<TreeMap<String,Object>>>>();

        String text行业 = "";
 
        
          
        String urlPath = "http://f10.eastmoney.com/f10_v2/BackOffice.aspx?command=RptF10MainTarget&code=";
        
        LinkedHashMap<String,List<TreeMap<String,Object>>> map = new LinkedHashMap<String, List<TreeMap<String,Object>>>();
        for(int i = 0 ; i < prepareData.size();i++){
          
          Integer integerCode = prepareData.get(i);
          if(9002822 == integerCode||
          603098 == integerCode ||
          603828 == integerCode ||
          603929 == integerCode){
            continue;
          }
          String strCode = "";
          String strCode2 = ""; 
          if(integerCode.toString().startsWith("9")){
        	  strCode = integerCode.toString().substring(1, integerCode.toString().length());
        	  
        	  strCode2 = "sz"+strCode;
        	  
        	  
        	  strCode+="02";
        	  
        	  
          }else{
            if(integerCode.toString().startsWith("3")){
              strCode2 = "sz"+integerCode;
              
              strCode=  integerCode.toString()+"02";
              
            }else{
              strCode2 = "sh"+integerCode.toString();
              
              strCode = integerCode.toString()+"01";
              
            }
            

          }
          
          
          String urlPath2 = urlPath+"";
          urlPath2 += strCode+"&num=9&code1="+strCode2+"&spstr=&n=2&timetip=1487063111208";
          
          //urlPath.append("code="+integerCode);
          
          
          System.out.println("请求地址urlPath2："+urlPath2);
          listUrl.add(urlPath2);
         
            File downFile = Get四季_以前的每年的四个季度的利润.downFile(urlPath2.toString(),strCode);
            
            String readFile = Get四季_以前的每年的四个季度的利润.readFile(downFile);
       
            
            //
            List<TreeMap<String,Object>> list = new ArrayList<TreeMap<String,Object>>();
            
            if(integerCode.toString().equals("603165")){
              System.out.println();
            }
            TreeMap<String, Object> sj = Get四季_以前的每年的四个季度的利润.sj(readFile,"归属净利润(元)");
            TreeMap<String, Object> sj2 = Get四季_以前的每年的四个季度的利润.sj(readFile,"扣非净利润(元)");
            list.add(sj);
            list.add(sj2);

            map.put(integerCode.toString(), list);
            
            
          }
          
          list行业.add(map);
          
         /* String text = "";
          for (String key : map.keySet()) {
              //map.keySet()返回的是所有key的值
              Object value = map.get(key);//得到每个key多对用value的值
              
              //System.out.println(key+"\t"+value);
              
              text += key+"\t"+value + "\r\n";
              
              //写入excel
              
          }
          
          System.out.println(text);
          	text行业 += text + "\r\n"+"\r\n"+"\r\n";
          */
          
          

        
        Get四季_以前的每年的四个季度的利润.Optexcel(list行业);
          
  }
  
  
  /**
   * 四季利润
   * @throws Exception 
   * 
   * */
  public static TreeMap<String,Object> sj(String readFile,String splitName) throws Exception{
    

    
    int local = readFile.indexOf("成长能力指标");//存货周转率(%)</td><td>5.33</td>
    String substringDate1 = "";
    substringDate1 = readFile.substring(local+57, local+65);

    
/*    if(substringDate1.equals("16-12-31")){
      System.out.println("======================================");
      throw new Exception("包含16-12-31");
    }
    */
    String substringDate2 = "";
    substringDate2 = readFile.substring(local+65+51, local+65+51+8);
    
    String substringDate3 = "";
    substringDate3 = readFile.substring(local+65+51+8+51, local+65+51+8+51+8);
    
    String substringDate4 = "";
    substringDate4 = readFile.substring(local+65+51+8+51+8+51, local+65+51+8+51+8+51+8);
    
    String substringDate5 = "";
    substringDate5 = readFile.substring(local+65+51+8+51+8+51+8+51, local+65+51+8+51+8+51+8+51+8);
    
    String substringDate6 = "";
    substringDate6 = readFile.substring(local+65+51+8+51+8+51+8+51+8+51, local+65+51+8+51+8+51+8+51+8+51+8);
    
    String substringDate7 = "";
    substringDate7 = readFile.substring(local+65+51+8+51+8+51+8+51+8+51+8+51, local+65+51+8+51+8+51+8+51+8+51+8+51+8);
    
    
    String substringDate8 = "";
    substringDate8 = readFile.substring(local+65+51+8+51+8+51+8+51+8+51+8+51+8+51, local+65+51+8+51+8+51+8+51+8+51+8+51+8+51+8);
    
    
    String substringDate9 = "";
    substringDate9 = readFile.substring(local+65+51+8+51+8+51+8+51+8+51+8+51+8+51+8+51, local+65+51+8+51+8+51+8+51+8+51+8+51+8+51+8+51+8);
    
    
    
    
    
   // int local3 = readFile.indexOf("归属净利润(元)");
    int local3 = readFile.indexOf(splitName);
    String substring = readFile.substring(local3+20, readFile.length());
    String all = substring.substring(0, substring.indexOf("</tr>"));
    
    /**
     * 从all拿出第一行出来
     * */
    String line1 = all.substring(0, all.indexOf("</td>"));
    String one = line1.substring(line1.indexOf("<span>")+6, line1.indexOf("</span>"));
    System.out.println(one);
    
    
    String remain2 = all.substring(all.indexOf("</td>")+5, all.length());
    
    
    String line2 = remain2.substring(0, remain2.indexOf("</td>"));
    String two = line2.substring(line2.indexOf("<span>")+6, line2.indexOf("</span>"));
    System.out.println(two);
    
    
    String remain3 = remain2.substring(remain2.indexOf("</td>")+5, remain2.length());
    
    String line3 = remain3.substring(0, remain3.indexOf("</td>"));
    String three = line3.substring(line3.indexOf("<span>")+6, line3.indexOf("</span>"));
    System.out.println(three);
    
    
    
    
    String remain4 = remain3.substring(remain3.indexOf("</td>")+5, remain3.length());
    String line4 = remain4.substring(0, remain4.indexOf("</td>"));
    String four = line4.substring(line4.indexOf("<span>")+6, line4.indexOf("</span>"));
    System.out.println(four);
    
    
    String remain5 = remain4.substring(remain4.indexOf("</td>")+5, remain4.length());
    String line5 = remain5.substring(0, remain5.indexOf("</td>"));
    String five = line5.substring(line5.indexOf("<span>")+6, line5.indexOf("</span>"));
    System.out.println(five);
    
    
    String remain6 = remain5.substring(remain5.indexOf("</td>")+5, remain5.length());
    String line6 = remain6.substring(0, remain6.indexOf("</td>"));
    String six = line6.substring(line6.indexOf("<span>")+6, line6.indexOf("</span>"));
    System.out.println(six);
    
    
    String remain7 = remain6.substring(remain6.indexOf("</td>")+5, remain6.length());
    String line7 = remain7.substring(0, remain7.indexOf("</td>"));
    String sev = line7.substring(line7.indexOf("<span>")+6, line7.indexOf("</span>"));
    System.out.println(sev);
    
    
    String remain8 = remain7.substring(remain7.indexOf("</td>")+5, remain7.length());
    String line8 = remain8.substring(0, remain8.indexOf("</td>"));
    String eg = line8.substring(line8.indexOf("<span>")+6, line8.indexOf("</span>"));
    System.out.println(eg);
    
    String remain9 = remain8.substring(remain8.indexOf("</td>")+5, remain8.length());
    String line9 = remain9.substring(0, remain9.indexOf("</td>"));
    String nig = line9.substring(line9.indexOf("<span>")+6, line9.indexOf("</span>"));
    System.out.println(nig);
    
    //substring2.substring(beginIndex, endIndex);
    
    
    TreeMap<String,Object> map2 = new TreeMap<String,Object>();
    
    map2.put(substringDate1, one);
    map2.put(substringDate2, two);
    map2.put(substringDate3, three);
    map2.put(substringDate4, four);
    map2.put(substringDate5, five);
    map2.put(substringDate6, six);
    map2.put(substringDate7, sev);
    map2.put(substringDate8, eg);
    map2.put(substringDate9, nig);
    return map2;
    
  }
  
  
  
  
  public static String outputFile = "C:\\Users\\Administrator.WINDOWS-8RP82UF\\Desktop\\test.xls";
  public static void Optexcel(List<LinkedHashMap<String,List<TreeMap<String,Object>>>> list行业) throws Exception{
	  
	  // 创建新的Excel 工作簿  
      HSSFWorkbook workbook = new HSSFWorkbook();  

      // 在Excel工作簿中建一工作表，其名为缺省值  
      // 如要新建一名为"效益指标"的工作表，其语句为：  
      // HSSFSheet sheet = workbook.createSheet("效益指标");  
      HSSFSheet sheet = workbook.createSheet();  
      
      

      
      int rownum = 0;
      
      // 在索引0的位置创建行（最顶端的行）  
     // HSSFRow row = sheet.createRow((short) 0);  
      
      for(int i = 0 ; i < list行业.size();i++){
       // Map<String, List<Map<String, Object>>> map = list行业.get(i);
        LinkedHashMap<String,List<TreeMap<String,Object>>> map = list行业.get(i);
        
        for(String codeKey : map.keySet()){
  
          List<TreeMap<String, Object>> list = map.get(codeKey);
          
          HSSFRow rowDate = null;
          //利润与扣非利润
          for(int j = 0 ; j < list.size() ; j++){
       
            TreeMap<String, Object> map2 = list.get(j);
            
            

            
            if(j == 0){
              rowDate = sheet.createRow((short) 0+rownum);
            }

            
            HSSFRow row1 = sheet.createRow((short) 1+j+rownum);
            
            int numCell = 1;
            int numCell2 = 1;
            for(String dateKey:map2.keySet()){
   
              if(j == 0 ){
                HSSFCell empCodeCellDate = rowDate.createCell((short) numCell++);  
                empCodeCellDate.setCellType(HSSFCell.CELL_TYPE_STRING);  
                empCodeCellDate.setCellValue(dateKey); 
                
              }
          
              Object value = map2.get(dateKey);
              HSSFCell empCodeCell = row1.createCell((short) numCell2++);  
              empCodeCell.setCellType(HSSFCell.CELL_TYPE_STRING);  
              empCodeCell.setCellValue(value.toString());  
              
              
              HSSFCell codeCell = row1.createCell((short) 0);
              codeCell.setCellType(HSSFCell.CELL_TYPE_STRING);
              codeCell.setCellValue(codeKey.toString());  
            }
          }
          
          rownum+=4;
          
        }
        
      }
      
      for(int i = 0 ; i < listUrl.size();i++){
        System.out.println(listUrl.get(i));
      }

    /*  HSSFCell empCodeCell = row.createCell((short) 1);  
      empCodeCell.setCellType(HSSFCell.CELL_TYPE_STRING);  
      empCodeCell.setCellValue("2004");  
        
      HSSFCell empNameCell = row.createCell((short) 2);  
      empNameCell.setCellType(HSSFCell.CELL_TYPE_STRING);  
      empNameCell.setCellValue("2005");  

      HSSFCell sexCell = row.createCell((short) 3);  
      sexCell.setCellType(HSSFCell.CELL_TYPE_STRING);  
      sexCell.setCellValue("2006");  
        
      HSSFCell birthdayCell = row.createCell((short) 4);  
      birthdayCell.setCellType(HSSFCell.CELL_TYPE_STRING);  
      birthdayCell.setCellValue("2007");  

      HSSFCell orgCodeCell = row.createCell((short) 5);  
      orgCodeCell.setCellType(HSSFCell.CELL_TYPE_STRING);  
      orgCodeCell.setCellValue("2008");  

      HSSFCell orgNameCell = row.createCell((short) 6);  
      orgNameCell.setCellType(HSSFCell.CELL_TYPE_STRING);  
      orgNameCell.setCellValue("2009");  
        
      HSSFCell contactTelCell = row.createCell((short) 7);  
      contactTelCell.setCellType(HSSFCell.CELL_TYPE_STRING);  
      contactTelCell.setCellValue("2010");  

      HSSFCell zjmCell = row.createCell((short) 8);  
      zjmCell.setCellType(HSSFCell.CELL_TYPE_STRING);  
      zjmCell.setCellValue("2011");  
      
      HSSFCell zjmCell2 = row.createCell((short) 9);  
      zjmCell2.setCellType(HSSFCell.CELL_TYPE_STRING);  
      zjmCell2.setCellValue("2012");  
      
      
      HSSFCell zjmCell3 = row.createCell((short) 10);  
      zjmCell3.setCellType(HSSFCell.CELL_TYPE_STRING);  
      zjmCell3.setCellValue("2013");  
      
      HSSFCell zjmCell4 = row.createCell((short) 11);  
      zjmCell4.setCellType(HSSFCell.CELL_TYPE_STRING);  
      zjmCell4.setCellValue("2014"); 
      
      
      HSSFCell zjmCell5 = row.createCell((short) 12);  
      zjmCell5.setCellType(HSSFCell.CELL_TYPE_STRING);  
      zjmCell5.setCellValue("2015"); */
      
      
/*      for(int i = 0 ; i < list行业.size();i++){
    	  Map<String, Object> map = list行业.get(i);
          HSSFRow ro = sheet.createRow((short) 1+i);
          int j = 0 ;
          
          
          HSSFCell ce = ro.createCell((short) 0);
          ce.setCellType(HSSFCell.CELL_TYPE_STRING);
            
          
          for (String key : map.keySet()) {
              //map.keySet()返回的是所有key的值
              Object value = map.get(key);//得到每个key多对用value的值
              
              
              if(j == 0){
            	  ce.setCellValue(key);
              }
              

              HSSFCell ce2 = ro.createCell((short) 1+j);
              ce2.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
              ce2.setCellValue(Double.parseDouble(value.toString())); 
              
              
              j++;
              //写入excel
              
          }
          
    	  
      }*/
      

      
      
 
      // 新建一输出文件流  
      FileOutputStream fOut = new FileOutputStream(outputFile);  
      // 把相应的Excel 工作簿存盘  
      workbook.write(fOut);  
      fOut.flush();  
      // 操作结束，关闭文件  
      fOut.close();  
      System.out.println("文件生成...");  

  }
  
  
  

  
  
  public static String readFile(File file) throws Exception{
    
    StringBuffer strb = new StringBuffer();  
    FileInputStream fs = new FileInputStream(file);  
    InputStreamReader isr = new InputStreamReader(fs,"UTF-8");  
    BufferedReader br = new BufferedReader(isr);  
    String data = "";  
    while((data = br.readLine()) != null){  
        strb.append(data + "\n");  
    }  
    br.close();  
    fs.close();  
    isr.close();  
   // System.out.println(strb.toString()); 
    return strb.toString();  
      
  }
  
  public static void writeFile(String str,String fileName) throws Exception{
       
      String directory = "C:\\Users\\kikili\\Desktop\\test2";
      File file = new File(directory,fileName);
     OutputStream oputstream = new FileOutputStream(file);  
     
     
     
     InputStream in=new ByteArrayInputStream(str.getBytes());
     
     byte[] buffer = new byte[4*1024];  
     int byteRead = -1;     
     while((byteRead=(in.read(buffer)))!= -1){  
         oputstream.write(buffer, 0, byteRead);  
     }  
     oputstream.flush();    
     in.close();  
     oputstream.close();  
     
     
  }
  
  
  
  
  public static File downFile(String urlPath,String fileName){
    File file = null;
    try {  
      
        String directory = "C:\\Users\\Administrator.WINDOWS-8RP82UF\\Desktop\\test";
    	// String directory = "C:\\Users\\kikili\\Desktop\\test";
       // String fileName = "myFile.html";
        
        
          file = new File(directory,fileName); 
      
        OutputStream oputstream = new FileOutputStream(file);  
        URL url = new URL(urlPath.toString());  
        HttpURLConnection uc = (HttpURLConnection) url.openConnection();  
        uc.setDoInput(true);//设置是否要从 URL 连接读取数据,默认为true  
        uc.connect();  
        InputStream iputstream = uc.getInputStream();  
        System.out.println("file size is:"+uc.getContentLength());//打印文件长度  
        byte[] buffer = new byte[4*1024];  
        int byteRead = -1;     
        while((byteRead=(iputstream.read(buffer)))!= -1){  
            oputstream.write(buffer, 0, byteRead);  
        }  
        oputstream.flush();    
        iputstream.close();  
        oputstream.close();  
      System.out.println(file.getAbsolutePath());
        return file;
          
 } catch (Exception e) {  
     System.out.println("读取失败！");  
     e.printStackTrace();  
 }  
    
    return file;
  }
  
  
  
  public static List<Integer> prepareData(){
    //1.化纤
    List<Integer> listCode_1_hq = new ArrayList<Integer>(Arrays.asList(
                                                  9000420  ,// 吉林化纤
                                                  9000584  ,// 友利控股
                                                  9000615  ,// 京汉股份
                                                  9000677   ,//恒天海龙
                                                  9000703   ,//恒逸石化
                                                  9000782   ,//美达股份
                                                  9000936  ,// 华西股份
                                                  9000949  ,// 新乡化纤
                                                  9000976  ,// 春晖股份
                                                  9002015  ,// 霞客环保
                                                  9002064  ,// 华峰氨纶
                                                  9002080  ,// 中材科技
                                                  9002172  ,// 澳洋科技
                                                  9002206   ,//海 利 得
                                                  9002254  ,// 泰和新材
                                                  9002427  ,// 尤夫股份
                                                  9002493   ,//荣盛石化
                                                  300180  ,// 华峰超纤
                                                  600063  ,// 皖维高新
                                                  600346  ,// 恒力股份
                                                  600527  ,// 江南高纤
                                                  600810  ,// 神马股份
                                                  600889  ,// 南京化纤
                                                  601113  ,// 华鼎股份
                                                  601233  // 桐昆股份

                                               )); 

    
    //造纸
    List<Integer> listCode_2_zz = new ArrayList<Integer>(Arrays.asList(
                                                 9000488 ,// 晨鸣纸业
                                                9000576 ,// 广东甘化
                                                9000815 ,// 美利云
                                                9000833 ,// 贵糖股份
                                                9002012 ,// 凯恩股份
                                                9002067 ,// 景兴纸业
                                                9002078 ,// 太阳纸业
                                                9002235 ,// 安妮股份
                                                9002303 ,// 美盈森
                                                9002511 ,// 中顺洁柔
                                                9002521 ,// 齐峰新材
                                                9002565 ,// 顺灏股份
                                                600069  ,// 银鸽投资
                                                600103  ,// 青山纸业
                                                600235  ,// 民丰特纸
                                                600308  ,// 华泰股份
                                                600356  ,// 恒丰纸业
                                                600433  ,// 冠豪高新
                                                600567  ,// 山鹰纸业
                                                600793  ,// 宜宾纸业
                                                600963  ,// 岳阳林纸
                                                600966  ,// 博汇纸业
                                                603165  // 荣晟环保
                                  ));
    //矿物制品
    List<Integer> listCode_3_kwzp = new ArrayList<Integer>(Arrays.asList(
                                                  9000511,//1.00 *ST烯碳
                                                  9000519,//2.00 江南红箭
                                                  9000795,//3.00 英洛华
                                                  9002088,//4.00 鲁阳节能
                                                  9002297,//5.00 博云新材
                                                  300064,//6.00 豫金刚石
                                                  300073,//7.00 当升科技
                                                  300179,//8.00 四方达
                                                  600172,//9.00 黄河旋风
                                                  600516,//10.00  方大炭素
                                                  600783,//11.00  鲁信创投
                                                  603663,//12.00  三祥新材
                                                  603688//13.00  石英股份
    
    ));
    
    //日用化工
    List<Integer> listCode_4_ryhg = new ArrayList<Integer>(Arrays.asList(
                                                9000523,//1.00  广州浪奇
                                                9000737,//2.00  南风化工
                                                9002094,//3.00  青岛金王
                                                9002637,//4.00  赞宇科技
                                                600249,//5.00 两面针
                                                600315//6.00 上海家化
    
    
    ));
    
    
    List<Integer> listCode_5_jydq = new ArrayList<Integer>(Arrays.asList(
                                              9000016,//1.00  深康佳Ａ  家用电器
                                              9000100,//2.00  TCL 集团  家用电器
                                              9000333,//3.00  美的集团  家用电器
                                              9000418,//4.00  小天鹅Ａ  家用电器
                                              9000521,//5.00  美菱电器  家用电器
                                              9000533,//6.00  万 家 乐 家用电器
                                              9000541,//7.00  佛山照明  家用电器
                                              9000651,//8.00  格力电器  家用电器
                                              9000921,//9.00  海信科龙  家用电器
                                              9002005,//10.00 德豪润达  家用电器
                                              9002032,//11.00 苏 泊 尔 家用电器
                                              9002035,//12.00 华帝股份  家用电器
                                              9002076,//13.00 雪 莱 特 家用电器
                                              9002242,//14.00 九阳股份  家用电器
                                              9002290,//15.00 禾盛新材  家用电器
                                              9002403,//16.00 爱仕达 家用电器
                                              9002429,//17.00 兆驰股份  家用电器
                                              9002473,//18.00 圣莱达 家用电器
                                              9002508,//19.00 老板电器  家用电器
                                              9002543,//20.00 万和电气  家用电器
                                              9002668,//21.00 奥马电器  家用电器
                                              9002677,//22.00 浙江美大  家用电器
                                              9002681,//23.00 奋达科技  家用电器
                                              9002705,//24.00 新宝股份  家用电器
                                              9002723,//25.00 金莱特 家用电器
                                              9002759,//26.00 天际股份  家用电器
                                              600060,//27.00  海信电器  家用电器
                                              600261,//28.00  阳光照明  家用电器
                                              600336,//29.00  澳柯玛 家用电器
                                              600690,//30.00  青岛海尔  家用电器
                                              600839,//31.00  四川长虹  家用电器
                                              600854,//32.00  春兰股份  家用电器
                                              600870,//33.00  厦华电子  家用电器
                                              600983,//34.00  惠而浦 家用电器
                                              603366,//35.00  日出东方  家用电器
                                              603519,//36.00  立霸股份  家用电器
                                              603868//37.00  飞科电器  家用电器

    
    ));
    
    List<Integer> listCode_6_ylbj = new ArrayList<Integer>(Arrays.asList(
                                                9000150,//1.00  宜华健康  医疗保健
                                                9000502,//2.00  绿景控股  医疗保健
                                                9000503,//3.00  海虹控股  医疗保健
                                                9002022,//4.00  科华生物  医疗保健
                                                9002044,//5.00  美年健康  医疗保健
                                                9002162,//6.00  悦心健康  医疗保健
                                                9002223,//7.00  鱼跃医疗  医疗保健
                                                9002382,//8.00  蓝帆医疗  医疗保健
                                                9002432,//9.00  九安医疗  医疗保健
                                                9002551,//10.00 尚荣医疗  医疗保健
                                                9002614,//11.00 蒙发利 医疗保健
                                                300003,//12.00  乐普医疗  医疗保健
                                                300015,//13.00  爱尔眼科  医疗保健
                                                300030,//14.00  阳普医疗  医疗保健
                                                300061,//15.00  康耐特 医疗保健
                                                300171,//16.00  东富龙 医疗保健
                                                300206,//17.00  理邦仪器  医疗保健
                                                300216,//18.00  千山药机  医疗保健
                                                300238,//19.00  冠昊生物  医疗保健
                                                300244,//20.00  迪安诊断  医疗保健
                                                300246,//21.00  宝莱特 医疗保健
                                                300247,//22.00  乐金健康  医疗保健
                                                300273,//23.00  和佳股份  医疗保健
                                                300298,//24.00  三诺生物  医疗保健
                                                300314,//25.00  戴维医疗  医疗保健
                                                300318,//26.00  博晖创新  医疗保健
                                                300326,//27.00  凯利泰 医疗保健
                                                300347,//28.00  泰格医药  医疗保健
                                                300358,//29.00  楚天科技  医疗保健
                                                300396,//30.00  迪瑞医疗  医疗保健
                                                300404,//31.00  博济医药  医疗保健
                                                300412,//32.00  迦南科技  医疗保健
                                                300439,//33.00  美康生物  医疗保健
                                                300453,//34.00  三鑫医疗  医疗保健
                                                300463,//35.00  迈克生物  医疗保健
                                                300529,//36.00  健帆生物  医疗保健
                                                300562,//37.00  乐心医疗  医疗保健
                                                600055,//38.00  万东医疗  医疗保健
                                                600381,//39.00  青海春天  医疗保健
                                                600530,//40.00  交大昂立  医疗保健
                                                600587,//41.00  新华医疗  医疗保健
                                                600763,//42.00  通策医疗  医疗保健
                                                600767,//43.00  运盛医疗  医疗保健
                                                603309,//44.00  维力医疗  医疗保健
                                                603658,//45.00  安图生物  医疗保健
                                                603987,//46.00  康德莱 医疗保健
                                                603579//47.00  荣泰健康  医疗保健

    
    ));
    
    
    List<Integer> listCode_7_jjyp = new ArrayList<Integer>(Arrays.asList(
                                                9000910,//1.00  大亚圣象  家居用品
                                                9002084,//2.00  海鸥卫浴  家居用品
                                                9002120,//3.00  新海股份  家居用品
                                                9002240,//4.00  威华股份  家居用品
                                                9002259,//5.00  升达林业  家居用品
                                                9002489,//6.00  浙江永强  家居用品
                                                9002572,//7.00  索菲亚 家居用品
                                                9002615,//8.00  哈尔斯 家居用品
                                                9002631,//9.00  德尔未来  家居用品
                                                9002718,//10.00 友邦吊顶  家居用品
                                                9002751,//11.00 易尚展示  家居用品
                                                9002757,//12.00 南兴装备  家居用品
                                                9002790,//13.00 瑞尔特 家居用品
                                                9002798,//14.00 帝王洁具  家居用品
                                                600337,//15.00  美克家居  家居用品
                                                600978,//16.00  宜华生活  家居用品
                                                603008,//17.00  喜临门 家居用品
                                                603313,//18.00  恒康家居  家居用品
                                                603389,//19.00  亚振家居  家居用品
                                                603600,//20.00  永艺股份  家居用品
                                                603816,//21.00  顾家家居  家居用品
                                                603818,//22.00  曲美家居  家居用品
                                                603898//23.00  好莱客 家居用品

    
    ));
    
    
    List<Integer> listCode_8_smdl = new ArrayList<Integer>(Arrays.asList(
                                                9000062,//1.00  深圳华强  商贸代理
                                                9000151,//2.00  中成股份  商贸代理
                                                9000408,//3.00  *ST金源 商贸代理
                                                9000626,//4.00  远大控股  商贸代理
                                                9002091,//5.00  江苏国泰  商贸代理
                                                600058,//6.00 五矿发展  商贸代理
                                                600120,//7.00 浙江东方  商贸代理
                                                600128,//8.00 弘业股份  商贸代理
                                                600153,//9.00 建发股份  商贸代理
                                                600241,//10.00  时代万恒  商贸代理
                                                600247,//11.00  ST成城  商贸代理
                                                600250,//12.00  南纺股份  商贸代理
                                                600278,//13.00  东方创业  商贸代理
                                                600287,//14.00  江苏舜天  商贸代理
                                                600382,//15.00  广东明珠  商贸代理
                                                600500,//16.00  中化国际  商贸代理
                                                600605,//17.00  汇通能源  商贸代理
                                                600704,//18.00  物产中大  商贸代理
                                                600735,//19.00  新华锦 商贸代理
                                                600739,//20.00  辽宁成大  商贸代理
                                                600755,//21.00  厦门国贸  商贸代理
                                                600822,//22.00  上海物贸  商贸代理
                                                600826,//23.00  兰生股份  商贸代理
                                                600981//24.00  汇鸿集团  商贸代理
    
    ));
    
    List<Integer> listCode_9_ggbz = new ArrayList<Integer>(Arrays.asList(
                                                  9000038,//1.00  深大通 广告包装
                                                  9000607,//2.00  华媒控股  广告包装
                                                  9000659,//3.00  珠海中富  广告包装
                                                  9000812,//4.00  陕西金叶  广告包装
                                                  9002117,//5.00  东港股份  广告包装
                                                  9002188,//6.00  巴士在线  广告包装
                                                  9002191,//7.00  劲嘉股份  广告包装
                                                  9002228,//8.00  合兴包装  广告包装
                                                  9002229,//9.00  鸿博股份  广告包装
                                                  9002400,//10.00 省广股份  广告包装
                                                  9002599,//11.00 盛通股份  广告包装
                                                  9002701,//12.00 奥瑞金 广告包装
                                                  9002712,//13.00 思美传媒  广告包装
                                                  9002752,//14.00 昇兴股份  广告包装
                                                  9002787,//15.00 华源包装  广告包装
                                                  9002799,//16.00 环球印务  广告包装
                                                  9002803,//17.00 吉宏股份  广告包装
                                                  9002812,//18.00 创新股份  广告包装
                                                  9002831,//19.00 裕同科技  广告包装
                                                  9002836,//20.00 新宏泽 广告包装
                                                  300057,//21.00  万顺股份  广告包装
                                                  300058,//22.00  蓝色光标  广告包装
                                                  300071,//23.00  华谊嘉信  广告包装
                                                  300501,//24.00  海顺新材  广告包装
                                                  600210,//25.00  紫江企业  广告包装
                                                  600836,//26.00  界龙实业  广告包装
                                                  600880,//27.00  博瑞传播  广告包装
                                                  601515,//28.00  东风股份  广告包装
                                                  601968,//29.00  宝钢包装  广告包装
                                                  603022,//30.00  新通联 广告包装
                                                  603058,//31.00  永吉股份  广告包装
                                                  603729//32.00  龙韵股份  广告包装
    
    ));
    
    
    List<Integer> listCode_10_wjxx = new ArrayList<Integer>(Arrays.asList(
                                        9000017,//1.00  深中华A  文教休闲
                                        9000526,//2.00  紫光学大  文教休闲
                                        9000558,//3.00  莱茵体育  文教休闲
                                        9002103,//4.00  广博股份  文教休闲
                                        9002105,//5.00  信隆健康  文教休闲
                                        9002301,//6.00  齐心集团  文教休闲
                                        9002348,//7.00  高乐股份  文教休闲
                                        9002575,//8.00  群兴玩具  文教休闲
                                        9002605,//9.00  姚记扑克  文教休闲
                                        9002678,//10.00 珠江钢琴  文教休闲
                                        300043,//11.00  星辉娱乐  文教休闲
                                        300329,//12.00  海伦钢琴  文教休闲
                                        300359,//13.00  全通教育  文教休闲
                                        600158,//14.00  中体产业  文教休闲
                                        600234,//15.00  *ST山水 文教休闲
                                        600661,//16.00  新南洋 文教休闲
                                        600679,//17.00  上海凤凰  文教休闲
                                        600818,//18.00  中路股份  文教休闲
                                        603398,//19.00  邦宝益智  文教休闲
                                        603899//20.00  晨光文具  文教休闲
    ));
    
    List<Integer> listCode_11_jdcy = new ArrayList<Integer>(Arrays.asList(

                                      9000007,//1.00  全新好 酒店餐饮
                                      9000428,//2.00  华天酒店  酒店餐饮
                                      9000524,//3.00  岭南控股  酒店餐饮
                                      9000721,//4.00  西安饮食  酒店餐饮
                                      9002186,//5.00  全 聚 德 酒店餐饮
                                      9002306,//6.00  中科云网  酒店餐饮
                                      9000033,//7.00  *ST新都 酒店餐饮
                                      600258,//8.00 首旅酒店  酒店餐饮
                                      600640,//9.00 号百控股  酒店餐饮
                                      600754,//10.00  锦江股份  酒店餐饮
                                      601007//11.00  金陵饭店  酒店餐饮
    ));
    
    List<Integer> listCode_12_hk = new ArrayList<Integer>(Arrays.asList(
                                    9000738,//1.00  中航动控  航空
                                    9000768,//2.00  中航飞机  航空
                                    9000901,//3.00  航天科技  航空
                                    9002013,//4.00  中航机电  航空
                                    9002023,//5.00  海特高新  航空
                                    9002111,//6.00  威海广泰  航空
                                    9002260,//7.00  德奥通航  航空
                                    300424,//8.00 航新科技  航空
                                    300581,//9.00 晨曦航空  航空
                                    600038,//10.00  中直股份  航空
                                    600118,//11.00  中国卫星  航空
                                    600316,//12.00  洪都航空  航空
                                    600343,//13.00  航天动力  航空
                                    600372,//14.00  中航电子  航空
                                    600391,//15.00  成发科技  航空
                                    600862,//16.00  中航高科  航空
                                    600879,//17.00  航天电子  航空
                                    600893//18.00  中航动力  航空
    ));
    
    List<Integer> listCode_13_cb = new ArrayList<Integer>(Arrays.asList(
                                  9002608,//1.00  *ST舜船 船舶
                                  300008,//2.00 天海防务  船舶
                                  300123,//3.00 太阳鸟 船舶
                                  300589,//4.00 江龙船艇  船舶
                                  600072,//5.00 钢构工程  船舶
                                  600150,//6.00 中国船舶  船舶
                                  600685,//7.00 中船防务  船舶
                                  601890,//8.00 亚星锚链  船舶
                                  601989//9.00 中国重工  船舶
    
    ));
    
    List<Integer> listCode_14_yssb = new ArrayList<Integer>(Arrays.asList(
                                9000008,//1.00  神州高铁  运输设备
                                9002367,//2.00  康力电梯  运输设备
                                9002689,//3.00  远大智能  运输设备
                                300011,//4.00 鼎汉技术  运输设备
                                300455,//5.00 康拓红外  运输设备
                                600495,//6.00 晋西车轴  运输设备
                                600835,//7.00 上海机电  运输设备
                                600894,//8.00 广日股份  运输设备
                                600967,//9.00 北方创业  运输设备
                                601313,//10.00  江南嘉捷  运输设备
                                601766,//11.00  中国中车  运输设备
                                603111,//12.00  康尼机电  运输设备
                                603611//13.00  诺力股份  运输设备
    
    ));
    
    
    List<Integer> listCode_15_dqsb = new ArrayList<Integer>(Arrays.asList(
                                      9000049,//1.00  德赛电池  电气设备
                                      9000400,//2.00  许继电气  电气设备
                                      9000585,//3.00  东北电气  电气设备
                                      9000682,//4.00  东方电子  电气设备
                                      9000922,//5.00  佳电股份  电气设备
                                      9000967,//6.00  盈峰环境  电气设备
                                      9002028,//7.00  思源电气  电气设备
                                      9002074,//8.00  国轩高科  电气设备
                                      9002112,//9.00  三变科技  电气设备
                                      9002130,//10.00 沃尔核材  电气设备
                                      9002164,//11.00 宁波东力  电气设备
                                      9002168,//12.00 深圳惠程  电气设备
                                      9002169,//13.00 智光电气  电气设备
                                      9002176,//14.00 江特电机  电气设备
                                      9002202,//15.00 金风科技  电气设备
                                      9002212,//16.00 南洋股份  电气设备
                                      9002227,//17.00 奥 特 迅 电气设备
                                      9002249,//18.00 大洋电机  电气设备
                                      9002266,//19.00 浙富控股  电气设备
                                      9002270,//20.00 华明装备  电气设备
                                      9002276,//21.00 万马股份  电气设备
                                      9002300,//22.00 太阳电缆  电气设备
                                      9002309,//23.00 中利科技  电气设备
                                      9002334,//24.00 英威腾 电气设备
                                      9002335,//25.00 科华恒盛  电气设备
                                      9002339,//26.00 积成电子  电气设备
                                      9002346,//27.00 柘中股份  电气设备
                                      9002350,//28.00 北京科锐  电气设备
                                      9002358,//29.00 森源电气  电气设备
                                      9002364,//30.00 中恒电气  电气设备
                                      9002380,//31.00 科远股份  电气设备
                                      9002451,//32.00 摩恩电气  电气设备
                                      9002452,//33.00 长高集团  电气设备
                                      9002471,//34.00 中超控股  电气设备
                                      9002498,//35.00 汉缆股份  电气设备
                                      9002531,//36.00 天顺风能  电气设备
                                      9002533,//37.00 金杯电工  电气设备
                                      9002546,//38.00 新联电子  电气设备
                                      9002560,//39.00 通达股份  电气设备
                                      9002576,//40.00 通达动力  电气设备
                                      9002580,//41.00 圣阳股份  电气设备
                                      9002606,//42.00 大连电瓷  电气设备
                                      9002610,//43.00 爱康科技  电气设备
                                      9002617,//44.00 露笑科技  电气设备
                                      9002622,//45.00 融钰集团  电气设备
                                      9002647,//46.00 宏磊股份  电气设备
                                      9002660,//47.00 茂硕电源  电气设备
                                      9002665,//48.00 首航节能  电气设备
                                      9002684,//49.00 猛狮科技  电气设备
                                      9002692,//50.00 远程电缆  电气设备
                                      9002706,//51.00 良信电器  电气设备
                                      9002730,//52.00 电光科技  电气设备
                                      9002733,//53.00 雄韬股份  电气设备
                                      9002782,//54.00 可立克 电气设备
                                      9002801,//55.00 微光股份  电气设备
                                      300001,//56.00  特锐德 电气设备
                                      300004,//57.00  南风股份  电气设备
                                      300018,//58.00  中元股份  电气设备
                                      300040,//59.00  九洲电气  电气设备
                                      300048,//60.00  合康新能  电气设备
                                      300062,//61.00  中能电气  电气设备
                                      300068,//62.00  南都电源  电气设备
                                      300069,//63.00  金利华电  电气设备
                                      300120,//64.00  经纬电材  电气设备
                                      300125,//65.00  易世达 电气设备
                                      300129,//66.00  泰胜风能  电气设备
                                      300140,//67.00  启源装备  电气设备
                                      300141,//68.00  和顺电气  电气设备
                                      300153,//69.00  科泰电源  电气设备
                                      300208,//70.00  恒顺众昇  电气设备
                                      300215,//71.00  电科院 电气设备
                                      300222,//72.00  科大智能  电气设备
                                      300252,//73.00  金信诺 电气设备
                                      300265,//74.00  通光线缆  电气设备
                                      300274,//75.00  阳光电源  电气设备
                                      300283,//76.00  温州宏丰  电气设备
                                      300341,//77.00  麦迪电气  电气设备
                                      300356,//78.00  光一科技  电气设备
                                      300376,//79.00  易事特 电气设备
                                      300403,//80.00  地尔汉宇  电气设备
                                      300407,//81.00  凯发电气  电气设备
                                      300423,//82.00  鲁亿通 电气设备
                                      300427,//83.00  红相电力  电气设备
                                      300438,//84.00  鹏辉能源  电气设备
                                      300444,//85.00  双杰电气  电气设备
                                      300447,//86.00  全信股份  电气设备
                                      300477,//87.00  合纵科技  电气设备
                                      300484,//88.00  蓝海华腾  电气设备
                                      300490,//89.00  华自科技  电气设备
                                      300491,//90.00  通合科技  电气设备
                                      300510,//91.00  金冠电气  电气设备
                                      300543,//92.00  朗科智能  电气设备
                                      300372,//93.00  欣泰电气  电气设备
                                      300593,//94.00  新雷能 电气设备
                                      600089,//95.00  特变电工  电气设备
                                      600110,//96.00  诺德股份  电气设备
                                      600112,//97.00  天成控股  电气设备
                                      600192,//98.00  长城电工  电气设备
                                      600202,//99.00  哈空调 电气设备
                                      600268,//100.00 国电南自  电气设备
                                      600290,//101.00 华仪电气  电气设备
                                      600312,//102.00 平高电气  电气设备
                                      600379,//103.00 宝光股份  电气设备
                                      600405,//104.00 动力源 电气设备
                                      600416,//105.00 湘电股份  电气设备
                                      600468,//106.00 百利电气  电气设备
                                      600482,//107.00 中国动力  电气设备
                                      600517,//108.00 置信电气  电气设备
                                      600525,//109.00 长园集团  电气设备
                                      600550,//110.00 保变电气  电气设备
                                      600560,//111.00 金自天正  电气设备
                                      600577,//112.00 精达股份  电气设备
                                      600580,//113.00 卧龙电气  电气设备
                                      600590,//114.00 泰豪科技  电气设备
                                      600847,//115.00 万里股份  电气设备
                                      600869,//116.00 智慧能源  电气设备
                                      600875,//117.00 东方电气  电气设备
                                      600885,//118.00 宏发股份  电气设备
                                      600973,//119.00 宝胜股份  电气设备
                                      601126,//120.00 四方股份  电气设备
                                      601179,//121.00 中国西电  电气设备
                                      601311,//122.00 骆驼股份  电气设备
                                      601558,//123.00 华锐风电  电气设备
                                      601616,//124.00 广电电气  电气设备
                                      601700,//125.00 风范股份  电气设备
                                      601727,//126.00 上海电气  电气设备
                                      601877,//127.00 正泰电器  电气设备
                                      603015,//128.00 弘讯科技  电气设备
                                      603016,//129.00 新宏泰 电气设备
                                      603333,//130.00 明星电缆  电气设备
                                      603355,//131.00 莱克电气  电气设备
                                      603416,//132.00 信捷电气  电气设备
                                      603515,//133.00 欧普照明  电气设备
                                      603606,//134.00 东方电缆  电气设备
                                      603618,//135.00 杭电股份  电气设备
                                      603703,//136.00 盛洋科技  电气设备
                                      603819,//137.00 神力股份  电气设备
                                      603861,//138.00 白云电器  电气设备
                                      603988,//139.00 中电电机  电气设备
                                      603628//140.00 清源股份  电气设备
                                      
    ));
    
    
    List<Integer> listCode_16_gcjx = new ArrayList<Integer>(Arrays.asList(
                                        9000157,//1.00  中联重科  工程机械
                                        9000425,//2.00  徐工机械  工程机械
                                        9000528,//3.00  柳 工 工程机械
                                        9000680,//4.00  山推股份  工程机械
                                        9000811,//5.00  烟台冰轮  工程机械
                                        9000923,//6.00  河北宣工  工程机械
                                        9002009,//7.00  天奇股份  工程机械
                                        9002011,//8.00  盾安环境  工程机械
                                        9002097,//9.00  山河智能  工程机械
                                        9002158,//10.00 汉钟精机  工程机械
                                        9002459,//11.00 天业通联  工程机械
                                        9002483,//12.00 润邦股份  工程机械
                                        9002523,//13.00 天桥起重  工程机械
                                        9002526,//14.00 山东矿机  工程机械
                                        9002535,//15.00 林州重机  工程机械
                                        9002667,//16.00 鞍重股份  工程机械
                                        9002685,//17.00 华东重机  工程机械
                                        300035,//18.00  中科电气  工程机械
                                        300103,//19.00  达刚路机  工程机械
                                        300185,//20.00  通裕重工  工程机械
                                        300308,//21.00  中际装备  工程机械
                                        600031,//22.00  三一重工  工程机械
                                        600169,//23.00  太原重工  工程机械
                                        600320,//24.00  振华重工  工程机械
                                        600582,//25.00  天地科技  工程机械
                                        600761,//26.00  安徽合力  工程机械
                                        600815,//27.00  厦工股份  工程机械
                                        600984,//28.00  建设机械  工程机械
                                        601100,//29.00  恒立液压  工程机械
                                        601106,//30.00  中国一重  工程机械
                                        601717,//31.00  郑煤机 工程机械
                                        603218,//32.00  日月股份  工程机械
                                        600710//33.00  *ST常林 工程机械
    ));
    
    
    List<Integer> listCode_17_dqyb = new ArrayList<Integer>(Arrays.asList(
                                        9000988,//1.00  华工科技  电器仪表
                                        9002008,//2.00  大族激光  电器仪表
                                        9002058,//3.00  威 尔 泰 电器仪表
                                        9002121,//4.00  科陆电子  电器仪表
                                        9002175,//5.00  东方网络  电器仪表
                                        9002197,//6.00  证通电子  电器仪表
                                        9002214,//7.00  大立科技  电器仪表
                                        9002236,//8.00  大华股份  电器仪表
                                        9002338,//9.00  奥普光电  电器仪表
                                        9002414,//10.00 高德红外  电器仪表
                                        9002415,//11.00 海康威视  电器仪表
                                        9002518,//12.00 科士达 电器仪表
                                        9002527,//13.00 新时达 电器仪表
                                        9002767,//14.00 先锋电子  电器仪表
                                        9002819,//15.00 东方中科  电器仪表
                                        300007,//16.00  汉威电子  电器仪表
                                        300012,//17.00  华测检测  电器仪表
                                        300066,//18.00  三川智慧  电器仪表
                                        300097,//19.00  智云股份  电器仪表
                                        300099,//20.00  尤洛卡 电器仪表
                                        300105,//21.00  龙源技术  电器仪表
                                        300112,//22.00  万讯自控  电器仪表
                                        300124,//23.00  汇川技术  电器仪表
                                        300165,//24.00  天瑞仪器  电器仪表
                                        300217,//25.00  东方电热  电器仪表
                                        300259,//26.00  新天科技  电器仪表
                                        300286,//27.00  安科瑞 电器仪表
                                        300306,//28.00  远方光电  电器仪表
                                        300338,//29.00  开元仪器  电器仪表
                                        300349,//30.00  金卡股份  电器仪表
                                        300354,//31.00  东华测试  电器仪表
                                        300360,//32.00  炬华科技  电器仪表
                                        300370,//33.00  安控科技  电器仪表
                                        300371,//34.00  汇中股份  电器仪表
                                        300410,//35.00  正业科技  电器仪表
                                        300416,//36.00  苏试试验  电器仪表
                                        300417,//37.00  南华仪器  电器仪表
                                        300430,//38.00  诚益通 电器仪表
                                        300445,//39.00  康斯特 电器仪表
                                        300466,//40.00  赛摩电气  电器仪表
                                        300480,//41.00  光力科技  电器仪表
                                        300515,//42.00  三德科技  电器仪表
                                        300516,//43.00  久之洋 电器仪表
                                        300553,//44.00  集智股份  电器仪表
                                        300557,//45.00  理工光科  电器仪表
                                        300567,//46.00  精测电子  电器仪表
                                        300572,//47.00  安车检测  电器仪表
                                        600366,//48.00  宁波韵升  电器仪表
                                        600651,//49.00  飞乐音响  电器仪表
                                        601222,//50.00  林洋能源  电器仪表
                                        601567,//51.00  三星医疗  电器仪表
                                        603100,//52.00  川仪股份  电器仪表
                                        603556//53.00  海兴电力  电器仪表
    ));
    
    List<Integer> listCode_18_dxyy = new ArrayList<Integer>(Arrays.asList(
                                      9002093,//1.00  国脉科技  电信运营
                                      9002467,//2.00  二六三 电信运营
                                      300017,//3.00 网宿科技  电信运营
                                      300383,//4.00 光环新网  电信运营
                                      600050,//5.00 中国联通  电信运营
                                      600804//6.00 鹏博士 电信运营
    ));
    
    List<Integer> listCode_19_ggjt = new ArrayList<Integer>(Arrays.asList(
                                      600386,//1.00 北巴传媒  公共交通
                                      600611,//2.00 大众交通  公共交通
                                      600650,//3.00 锦江投资  公共交通
                                      600662,//4.00 强生控股  公共交通
                                      600676,//5.00 交运股份  公共交通
                                      600834//6.00 申通地铁  公共交通
    ));
    
    List<Integer> listCode_20_sw = new ArrayList<Integer>(Arrays.asList(
                                    9000598,//1.00  兴蓉环境  水务
                                    9000605,//2.00  渤海股份  水务
                                    9000685,//3.00  中山公用  水务
                                    600168,//4.00 武汉控股  水务
                                    600187,//5.00 国中水务  水务
                                    600283,//6.00 钱江水利  水务
                                    600323,//7.00 瀚蓝环境  水务
                                    600461,//8.00 洪城水业  水务
                                    601158,//9.00 重庆水务  水务
                                    601199,//10.00  江南水务  水务
                                    601368//11.00  绿城水务  水务
    ));
    
    List<Integer> listCode_21_gsgr = new ArrayList<Integer>(Arrays.asList(
                                  9000407,//1.00  胜利股份  供气供热
                                  9000421,//2.00  南京公用  供气供热
                                  9000593,//3.00  大通燃气  供气供热
                                  9000669,//4.00  金鸿能源  供气供热
                                  9000692,//5.00  惠天热电  供气供热
                                  9000695,//6.00  滨海能源  供气供热
                                  9002267,//7.00  陕天然气  供气供热
                                  9002524,//8.00  光正集团  供气供热
                                  9002700,//9.00  新疆浩源  供气供热
                                  300335,//10.00  迪森股份  供气供热
                                  600167,//11.00  联美控股  供气供热
                                  600333,//12.00  长春燃气  供气供热
                                  600617,//13.00  国新能源  供气供热
                                  600635,//14.00  大众公用  供气供热
                                  600681,//15.00  百川能源  供气供热
                                  600719,//16.00  大连热电  供气供热
                                  600856,//17.00  中天能源  供气供热
                                  600917,//18.00  重庆燃气  供气供热
                                  600982,//19.00  宁波热电  供气供热
                                  601139,//20.00  深圳燃气  供气供热
                                  603393,//21.00  新天然气  供气供热
                                  603689//22.00  皖天然气  供气供热
    ));
    
    List<Integer> listCode_22_hjbh = new ArrayList<Integer>(Arrays.asList(
                                9000035,//1.00  中国天楹  环境保护
                                9000544,//2.00  中原环保  环境保护
                                9000820,//3.00  神雾节能  环境保护
                                9000826,//4.00  启迪桑德  环境保护
                                9000920,//5.00  南方汇通  环境保护
                                9002200,//6.00  云投生态  环境保护
                                9002322,//7.00  理工环科  环境保护
                                9002499,//8.00  科林环保  环境保护
                                9002549,//9.00  凯美特气  环境保护
                                9002573,//10.00 清新环境  环境保护
                                9002616,//11.00 长青集团  环境保护
                                9002658,//12.00 雪迪龙 环境保护
                                9002672,//13.00 东江环保  环境保护
                                300055,//14.00  万邦达 环境保护
                                300056,//15.00  三维丝 环境保护
                                300070,//16.00  碧水源 环境保护
                                300072,//17.00  三聚环保  环境保护
                                300090,//18.00  盛运环保  环境保护
                                300137,//19.00  先河环保  环境保护
                                300152,//20.00  科融环境  环境保护
                                300156,//21.00  神雾环保  环境保护
                                300172,//22.00  中电环保  环境保护
                                300187,//23.00  永清环保  环境保护
                                300190,//24.00  维尔利 环境保护
                                300197,//25.00  铁汉生态  环境保护
                                300203,//26.00  聚光科技  环境保护
                                300262,//27.00  巴安水务  环境保护
                                300272,//28.00  开能环保  环境保护
                                300332,//29.00  天壕环境  环境保护
                                300355,//30.00  蒙草生态  环境保护
                                300362,//31.00  天翔环境  环境保护
                                300385,//32.00  雪浪环境  环境保护
                                300388,//33.00  国祯环保  环境保护
                                300422,//34.00  博世科 环境保护
                                300425,//35.00  环能科技  环境保护
                                600008,//36.00  首创股份  环境保护
                                600217,//37.00  中再资环  环境保护
                                600292,//38.00  远达环保  环境保护
                                600388,//39.00  龙净环保  环境保护
                                600481,//40.00  双良节能  环境保护
                                600526,//41.00  菲达环保  环境保护
                                600874,//42.00  创业环保  环境保护
                                603126,//43.00  中材节能  环境保护
                                603568,//44.00  伟明环保  环境保护
                                603588,//45.00  高能环境  环境保护
                                603822//46.00  嘉澳环保  环境保护
    ));
    
    List<Integer> listCode_23_ccwl = new ArrayList<Integer>(Arrays.asList(
                                9002183,//1.00  怡 亚 通 仓储物流
                                9002210,//2.00  飞马国际  仓储物流
                                9002245,//3.00  澳洋顺昌  仓储物流
                                9002468,//4.00  申通快递  仓储物流
                                9002492,//5.00  恒基达鑫  仓储物流
                                9002711,//6.00  欧浦智网  仓储物流
                                9002769,//7.00  普路通 仓储物流
                                9002800,//8.00  天顺股份  仓储物流
                                300013,//9.00 新宁物流  仓储物流
                                300240,//10.00  飞力达 仓储物流
                                300350,//11.00  华鹏飞 仓储物流
                                600057,//12.00  象屿股份  仓储物流
                                600119,//13.00  长江投资  仓储物流
                                600179,//14.00  安通控股  仓储物流
                                600180,//15.00  瑞茂通 仓储物流
                                600233,//16.00  圆通速递  仓储物流
                                600270,//17.00  外运发展  仓储物流
                                600787,//18.00  中储股份  仓储物流
                                600794,//19.00  保税科技  仓储物流
                                603117,//20.00  万林股份  仓储物流
                                603128,//21.00  华贸物流  仓储物流
                                603569//22.00  长久物流  仓储物流
    
    ));
    
    List<Integer> listCode_24_yh = new ArrayList<Integer>(Arrays.asList(
                                        9000001,//1.00  平安银行  银行
                                        9002142,//2.00  宁波银行  银行
                                        9002807,//3.00  江阴银行  银行
                                        9002839,//4.00  张家港行  银行
                                        600000,//5.00 浦发银行  银行
                                        600015,//6.00 华夏银行  银行
                                        600016,//7.00 民生银行  银行
                                        600036,//8.00 招商银行  银行
                                        600908,//9.00 无锡银行  银行
                                        600919,//10.00  江苏银行  银行
                                        600926,//11.00  杭州银行  银行
                                        601009,//12.00  南京银行  银行
                                        601128,//13.00  常熟银行  银行
                                        601166,//14.00  兴业银行  银行
                                        601169,//15.00  北京银行  银行
                                        601229,//16.00  上海银行  银行
                                        601288,//17.00  农业银行  银行
                                        601328,//18.00  交通银行  银行
                                        601398,//19.00  工商银行  银行
                                        601818,//20.00  光大银行  银行
                                        601939,//21.00  建设银行  银行
                                        601988,//22.00  中国银行  银行
                                        601997,//23.00  贵阳银行  银行
                                        601998,//24.00  中信银行  银行
                                        603323//25.00  吴江银行  银行
    ));
    List<Integer> listCode_25_zq = new ArrayList<Integer>(Arrays.asList(
                                      9000166,//1.00  申万宏源  证券
                                      9000686,//2.00  东北证券  证券
                                      9000728,//3.00  国元证券  证券
                                      9000750,//4.00  国海证券  证券
                                      9000776,//5.00  广发证券  证券
                                      9000783,//6.00  长江证券  证券
                                      9002500,//7.00  山西证券  证券
                                      9002673,//8.00  西部证券  证券
                                      9002736,//9.00  国信证券  证券
                                      9002797,//10.00 第一创业  证券
                                      600030,//11.00  中信证券  证券
                                      600061,//12.00  国投安信  证券
                                      600109,//13.00  国金证券  证券
                                      600369,//14.00  西南证券  证券
                                      600837,//15.00  海通证券  证券
                                      600909,//16.00  华安证券  证券
                                      600958,//17.00  东方证券  证券
                                      600999,//18.00  招商证券  证券
                                      601099,//19.00  太平洋 证券
                                      601198,//20.00  东兴证券  证券
                                      601211,//21.00  国泰君安  证券
                                      601377,//22.00  兴业证券  证券
                                      601555,//23.00  东吴证券  证券
                                      601688,//24.00  华泰证券  证券
                                      601788,//25.00  光大证券  证券
                                      601901,//26.00  方正证券  证券
                                      601375,//27.00  中原证券  证券
                                      601881//28.00  中国银河  证券
    ));
    
    List<Integer> listCode_26_bx = new ArrayList<Integer>(Arrays.asList(
                                      9000627,//1.00  天茂集团  保险
                                      600291,//2.00 西水股份  保险
                                      601318,//3.00 中国平安  保险
                                      601336,//4.00 新华保险  保险
                                      601601,//5.00 中国太保  保险
                                      601628//6.00 中国人寿  保险
    ));
    
    List<Integer> listCode_27_dyjr = new ArrayList<Integer>(Arrays.asList(
                                      9000415,//1.00  渤海金控  多元金融
                                      9000416,//2.00  民生控股  多元金融
                                      9000563,//3.00  陕国投Ａ  多元金融
                                      9000712,//4.00  锦龙股份  多元金融
                                      9000987,//5.00  越秀金控  多元金融
                                      9002670,//6.00  国盛金控  多元金融
                                      600318,//7.00 新力金融  多元金融
                                      600599,//8.00 熊猫金控  多元金融
                                      600643,//9.00 爱建集团  多元金融
                                      600695,//10.00  绿庭投资  多元金融
                                      600705,//11.00  中航资本  多元金融
                                      600747,//12.00  大连控股  多元金融
                                      600816,//13.00  安信信托  多元金融
                                      600830//14.00  香溢融通  多元金融
    
    ));
    
    List<Integer> listCode_28_dlsb = new ArrayList<Integer>(Arrays.asList(
                                      9000021,//1.00  深科技 电脑设备
                                      9000066,//2.00  长城电脑  电脑设备
                                      9000748,//3.00  长城信息  电脑设备
                                      9000977,//4.00  浪潮信息  电脑设备
                                      9002152,//5.00  广电运通  电脑设备
                                      9002177,//6.00  御银股份  电脑设备
                                      9002180,//7.00  艾派克 电脑设备
                                      9002308,//8.00  威创股份  电脑设备
                                      9002312,//9.00  三泰控股  电脑设备
                                      9002351,//10.00 漫步者 电脑设备
                                      9002362,//11.00 汉王科技  电脑设备
                                      9002376,//12.00 新北洋 电脑设备
                                      9002528,//13.00 英飞拓 电脑设备
                                      9002577,//14.00 雷柏科技  电脑设备
                                      9002635,//15.00 安洁科技  电脑设备
                                      300042,//16.00  朗科科技  电脑设备
                                      300045,//17.00  华力创通  电脑设备
                                      300076,//18.00  GQY视讯 电脑设备
                                      300130,//19.00  新国都 电脑设备
                                      300282,//20.00  汇冠股份  电脑设备
                                      300367,//21.00  东方网力  电脑设备
                                      300390,//22.00  天华超净  电脑设备
                                      300449,//23.00  汉邦高科  电脑设备
                                      600074,//24.00  保千里 电脑设备
                                      600100,//25.00  同方股份  电脑设备
                                      600271,//26.00  航天信息  电脑设备
                                      600601,//27.00  方正科技  电脑设备
                                      600734,//28.00  实达集团  电脑设备
                                      603019,//29.00  中科曙光  电脑设备
                                      603025,//30.00  大豪科技  电脑设备
                                      603996//31.00  中新科技  电脑设备
    
    ));
    
    List<Integer> listCode_29_txsb = new ArrayList<Integer>(Arrays.asList(
                                      9000063,//1.00  中兴通讯  通信设备
                                      9000070,//2.00  特发信息  通信设备
                                      9000547,//3.00  航天发展  通信设备
                                      9000561,//4.00  烽火电子  通信设备
                                      9000586,//5.00  汇源通信  通信设备
                                      9000687,//6.00  华讯方舟  通信设备
                                      9000801,//7.00  四川九洲  通信设备
                                      9000836,//8.00  鑫茂科技  通信设备
                                      9000889,//9.00  茂业通信  通信设备
                                      9000892,//10.00 星美联合  通信设备
                                      9002017,//11.00 东信和平  通信设备
                                      9002052,//12.00 同洲电子  通信设备
                                      9002089,//13.00 新 海 宜 通信设备
                                      9002115,//14.00 三维通信  通信设备
                                      9002151,//15.00 北斗星通  通信设备
                                      9002161,//16.00 远 望 谷 通信设备
                                      9002194,//17.00 武汉凡谷  通信设备
                                      9002231,//18.00 奥维通信  通信设备
                                      9002281,//19.00 光迅科技  通信设备
                                      9002296,//20.00 辉煌科技  通信设备
                                      9002313,//21.00 日海通讯  通信设备
                                      9002369,//22.00 卓翼科技  通信设备
                                      9002383,//23.00 合众思壮  通信设备
                                      9002384,//24.00 东山精密  通信设备
                                      9002396,//25.00 星网锐捷  通信设备
                                      9002413,//26.00 雷科防务  通信设备
                                      9002417,//27.00 三元达 通信设备
                                      9002446,//28.00 盛路通信  通信设备
                                      9002465,//29.00 海格通信  通信设备
                                      9002491,//30.00 通鼎互联  通信设备
                                      9002519,//31.00 银河电子  通信设备
                                      9002547,//32.00 春兴精工  通信设备
                                      9002583,//33.00 海能达 通信设备
                                      9002792,//34.00 通宇通讯  通信设备
                                      9002829,//35.00 星网宇达  通信设备
                                      300025,//36.00  华星创业  通信设备
                                      300028,//37.00  金亚科技  通信设备
                                      300038,//38.00  梅泰诺 通信设备
                                      300074,//39.00  华平股份  通信设备
                                      300079,//40.00  数码视讯  通信设备
                                      300081,//41.00  恒信移动  通信设备
                                      300098,//42.00  高新兴 通信设备
                                      300101,//43.00  振芯科技  通信设备
                                      300134,//44.00  大富科技  通信设备
                                      300136,//45.00  信维通信  通信设备
                                      300167,//46.00  迪威视讯  通信设备
                                      300177,//47.00  中海达 通信设备
                                      300211,//48.00  亿通科技  通信设备
                                      300213,//49.00  佳讯飞鸿  通信设备
                                      300250,//50.00  初灵信息  通信设备
                                      300264,//51.00  佳创视讯  通信设备
                                      300270,//52.00  中威电子  通信设备
                                      300292,//53.00  吴通控股  通信设备
                                      300299,//54.00  富春通信  通信设备
                                      300310,//55.00  宜通世纪  通信设备
                                      300312,//56.00  邦讯技术  通信设备
                                      300322,//57.00  硕贝德 通信设备
                                      300353,//58.00  东土科技  通信设备
                                      300394,//59.00  天孚通信  通信设备
                                      300397,//60.00  天和防务  通信设备
                                      300493,//61.00  润欣科技  通信设备
                                      300502,//62.00  新易盛 通信设备
                                      300555,//63.00  路通视信  通信设备
                                      300560,//64.00  中富通 通信设备
                                      300563,//65.00  神宇股份  通信设备
                                      300565,//66.00  科信技术  通信设备
                                      300590,//67.00  移为通信  通信设备
                                      600105,//68.00  永鼎股份  通信设备
                                      600130,//69.00  波导股份  通信设备
                                      600198,//70.00  大唐电信  通信设备
                                      600260,//71.00  凯乐科技  通信设备
                                      600345,//72.00  长江通信  通信设备
                                      600485,//73.00  信威集团  通信设备
                                      600487,//74.00  亨通光电  通信设备
                                      600498,//75.00  烽火通信  通信设备
                                      600522,//76.00  中天科技  通信设备
                                      600562,//77.00  国睿科技  通信设备
                                      600677,//78.00  航天通信  通信设备
                                      600680,//79.00  上海普天  通信设备
                                      600745,//80.00  中茵股份  通信设备
                                      600764,//81.00  中电广通  通信设备
                                      600775,//82.00  南京熊猫  通信设备
                                      600776,//83.00  东方通信  通信设备
                                      600990,//84.00  四创电子  通信设备
                                      603118,//85.00  共进股份  通信设备
                                      603322,//86.00  超讯通信  通信设备
                                      603421,//87.00  鼎信通讯  通信设备
                                      603559,//88.00  中通国脉  通信设备
                                      603660//89.00  苏州科达  通信设备
    ));
    
    List<Integer> listCode_30_bdt = new ArrayList<Integer>(Arrays.asList(
                                      9002079,//1.00  苏州固锝  半导体
                                      9002119,//2.00  康强电子  半导体
                                      9002129,//3.00  中环股份  半导体
                                      9002156,//4.00  通富微电  半导体
                                      9002185,//5.00  华天科技  半导体
                                      9002218,//6.00  拓日新能  半导体
                                      9002371,//7.00  七星电子  半导体
                                      9002449,//8.00  国星光电  半导体
                                      9002506,//9.00  协鑫集成  半导体
                                      9002638,//10.00 勤上光电  半导体
                                      9002654,//11.00 万润科技  半导体
                                      9002724,//12.00 海洋王 半导体
                                      9002745,//13.00 木林森 半导体
                                      9002815,//14.00 崇达技术  半导体
                                      300046,//15.00  台基股份  半导体
                                      300053,//16.00  欧比特 半导体
                                      300077,//17.00  国民技术  半导体
                                      300080,//18.00  易成新能  半导体
                                      300102,//19.00  乾照光电  半导体
                                      300111,//20.00  向日葵 半导体
                                      300118,//21.00  东方日升  半导体
                                      300223,//22.00  北京君正  半导体
                                      300232,//23.00  洲明科技  半导体
                                      300241,//24.00  瑞丰光电  半导体
                                      300269,//25.00  联建光电  半导体
                                      300296,//26.00  利亚德 半导体
                                      300301,//27.00  长方集团  半导体
                                      300303,//28.00  聚飞光电  半导体
                                      300317,//29.00  珈伟股份  半导体
                                      300323,//30.00  华灿光电  半导体
                                      300327,//31.00  中颖电子  半导体
                                      300373,//32.00  扬杰科技  半导体
                                      300389,//33.00  艾比森 半导体
                                      300582,//34.00  英飞特 半导体
                                      600151,//35.00  航天机电  半导体
                                      600171,//36.00  上海贝岭  半导体
                                      600206,//37.00  有研新材  半导体
                                      600360,//38.00  华微电子  半导体
                                      600401,//39.00  海润光伏  半导体
                                      600460,//40.00  士兰微 半导体
                                      600537,//41.00  亿晶光电  半导体
                                      600584,//42.00  长电科技  半导体
                                      600667,//43.00  太极实业  半导体
                                      600703,//44.00  三安光电  半导体
                                      600817,//45.00  *ST宏盛 半导体
                                      601012,//46.00  隆基股份  半导体
                                      601908,//47.00  京运通 半导体
                                      603005,//48.00  晶方科技  半导体
                                      603986//49.00  兆易创新  半导体
    
    ));
    
    List<Integer> listCode_31_yqj = new ArrayList<Integer>(Arrays.asList(
                                    9000020,//1.00  深华发Ａ  元器件
                                    9000032,//2.00  深桑达Ａ  元器件
                                    9000050,//3.00  深天马Ａ  元器件
                                    9000058,//4.00  深 赛 格 元器件
                                    9000068,//5.00  华控赛格  元器件
                                    9000413,//6.00  东旭光电  元器件
                                    9000532,//7.00  力合股份  元器件
                                    9000536,//8.00  华映科技  元器件
                                    9000636,//9.00  风华高科  元器件
                                    9000670,//10.00 *ST盈方 元器件
                                    9000725,//11.00 京东方Ａ  元器件
                                    9000727,//12.00 华东科技  元器件
                                    9000733,//13.00 振华科技  元器件
                                    9000810,//14.00 创维数字  元器件
                                    9000823,//15.00 超声电子  元器件
                                    9000970,//16.00 中科三环  元器件
                                    9002025,//17.00 航天电器  元器件
                                    9002036,//18.00 联创电子  元器件
                                    9002045,//19.00 国光电器  元器件
                                    9002049,//20.00 紫光国芯  元器件
                                    9002055,//21.00 得润电子  元器件
                                    9002056,//22.00 横店东磁  元器件
                                    9002057,//23.00 中钢天源  元器件
                                    9002104,//24.00 恒宝股份  元器件
                                    9002106,//25.00 莱宝高科  元器件
                                    9002134,//26.00 天津普林  元器件
                                    9002137,//27.00 麦达数字  元器件
                                    9002138,//28.00 顺络电子  元器件
                                    9002139,//29.00 拓邦股份  元器件
                                    9002141,//30.00 蓉胜超微  元器件
                                    9002179,//31.00 中航光电  元器件
                                    9002189,//32.00 利达光电  元器件
                                    9002199,//33.00 *ST东晶 元器件
                                    9002217,//34.00 合力泰 元器件
                                    9002222,//35.00 福晶科技  元器件
                                    9002241,//36.00 歌尔股份  元器件
                                    9002273,//37.00 水晶光电  元器件
                                    9002288,//38.00 超华科技  元器件
                                    9002289,//39.00 *ST宇顺 元器件
                                    9002388,//40.00 新亚制程  元器件
                                    9002389,//41.00 南洋科技  元器件
                                    9002402,//42.00 和而泰 元器件
                                    9002426,//43.00 胜利精密  元器件
                                    9002436,//44.00 兴森科技  元器件
                                    9002456,//45.00 欧菲光 元器件
                                    9002463,//46.00 沪电股份  元器件
                                    9002475,//47.00 立讯精密  元器件
                                    9002484,//48.00 江海股份  元器件
                                    9002512,//49.00 达华智能  元器件
                                    9002579,//50.00 中京电子  元器件
                                    9002587,//51.00 奥拓电子  元器件
                                    9002600,//52.00 江粉磁材  元器件
                                    9002618,//53.00 丹邦科技  元器件
                                    9002636,//54.00 金安国纪  元器件
                                    9002655,//55.00 共达电声  元器件
                                    9002729,//56.00 好利来 元器件
                                    9002806,//57.00 华锋股份  元器件
                                    9002808,//58.00 苏州恒久  元器件
                                    9002835,//59.00 同为股份  元器件
                                    300014,//60.00  亿纬锂能  元器件
                                    300032,//61.00  金龙机电  元器件
                                    300078,//62.00  思创医惠  元器件
                                    300083,//63.00  劲胜精密  元器件
                                    300088,//64.00  长信科技  元器件
                                    300114,//65.00  中航电测  元器件
                                    300115,//66.00  长盈精密  元器件
                                    300127,//67.00  银河磁体  元器件
                                    300128,//68.00  锦富新材  元器件
                                    300131,//69.00  英唐智控  元器件
                                    300139,//70.00  晓程科技  元器件
                                    300154,//71.00  瑞凌股份  元器件
                                    300155,//72.00  安居宝 元器件
                                    300162,//73.00  雷曼股份  元器件
                                    300184,//74.00  力源信息  元器件
                                    300205,//75.00  天喻信息  元器件
                                    300207,//76.00  欣旺达 元器件
                                    300219,//77.00  鸿利智汇  元器件
                                    300220,//78.00  金运激光  元器件
                                    300224,//79.00  正海磁材  元器件
                                    300227,//80.00  光韵达 元器件
                                    300256,//81.00  星星科技  元器件
                                    300279,//82.00  和晶科技  元器件
                                    300319,//83.00  麦捷科技  元器件
                                    300331,//84.00  苏大维格  元器件
                                    300333,//85.00  兆日科技  元器件
                                    300340,//86.00  科恒股份  元器件
                                    300342,//87.00  天银机电  元器件
                                    300346,//88.00  南大光电  元器件
                                    300351,//89.00  永贵电器  元器件
                                    300408,//90.00  三环集团  元器件
                                    300414,//91.00  中光防雷  元器件
                                    300433,//92.00  蓝思科技  元器件
                                    300456,//93.00  耐威科技  元器件
                                    300458,//94.00  全志科技  元器件
                                    300460,//95.00  惠伦晶体  元器件
                                    300474,//96.00  景嘉微 元器件
                                    300476,//97.00  胜宏科技  元器件
                                    300546,//98.00  雄帝科技  元器件
                                    300548,//99.00  博创科技  元器件
                                    300566,//100.00 激智科技  元器件
                                    300570,//101.00 太辰光 元器件
                                    9002841,//102.00 视源股份  元器件
                                    600071,//103.00 凤凰光学  元器件
                                    600183,//104.00 生益科技  元器件
                                    600203,//105.00 福日电子  元器件
                                    600207,//106.00 安彩高科  元器件
                                    600237,//107.00 铜峰电子  元器件
                                    600330,//108.00 天通股份  元器件
                                    600353,//109.00 旭光股份  元器件
                                    600355,//110.00 精伦电子  元器件
                                    600363,//111.00 联创光电  元器件
                                    600462,//112.00 九有股份  元器件
                                    600478,//113.00 科力远 元器件
                                    600563,//114.00 法拉电子  元器件
                                    600666,//115.00 奥瑞德 元器件
                                    600707,//116.00 彩虹股份  元器件
                                    600980,//117.00 北矿科技  元器件
                                    601231,//118.00 环旭电子  元器件
                                    603160,//119.00 汇顶科技  元器件
                                    603328,//120.00 依顿电子  元器件
                                    603633,//121.00 徕木股份  元器件
                                    603678,//122.00 火炬电子  元器件
                                    603738,//123.00 泰晶科技  元器件
                                    603936,//124.00 博敏电子  元器件
                                    603989,//125.00 艾华集团  元器件
                                    603186,//126.00 华正新材  元器件
                                    603228//127.00 景旺电子  元器件
    
    ));
    
    List<Integer> listCode_32_rjfw = new ArrayList<Integer>(Arrays.asList(
                                    9000555,//1.00  神州信息  软件服务
                                    9000662,//2.00  天夏智慧  软件服务
                                    9000711,//3.00  京蓝科技  软件服务
                                    9000851,//4.00  高鸿股份  软件服务
                                    9000938,//5.00  紫光股份  软件服务
                                    9000948,//6.00  南天信息  软件服务
                                    9000997,//7.00  新 大 陆 软件服务
                                    9002027,//8.00  分众传媒  软件服务
                                    9002063,//9.00  远光软件  软件服务
                                    9002065,//10.00 东华软件  软件服务
                                    9002073,//11.00 软控股份  软件服务
                                    9002090,//12.00 金智科技  软件服务
                                    9002153,//13.00 石基信息  软件服务
                                    9002184,//14.00 海得控制  软件服务
                                    9002195,//15.00 二三四五  软件服务
                                    9002230,//16.00 科大讯飞  软件服务
                                    9002232,//17.00 启明信息  软件服务
                                    9002253,//18.00 川大智胜  软件服务
                                    9002268,//19.00 卫 士 通 软件服务
                                    9002279,//20.00 久其软件  软件服务
                                    9002280,//21.00 联络互动  软件服务
                                    9002298,//22.00 中电鑫龙  软件服务
                                    9002316,//23.00 键桥通讯  软件服务
                                    9002331,//24.00 皖通科技  软件服务
                                    9002368,//25.00 太极股份  软件服务
                                    9002373,//26.00 千方科技  软件服务
                                    9002401,//27.00 中海科技  软件服务
                                    9002405,//28.00 四维图新  软件服务
                                    9002410,//29.00 广联达 软件服务
                                    9002421,//30.00 达实智能  软件服务
                                    9002439,//31.00 启明星辰  软件服务
                                    9002474,//32.00 榕基软件  软件服务
                                    9002544,//33.00 杰赛科技  软件服务
                                    9002609,//34.00 捷顺科技  软件服务
                                    9002642,//35.00 荣之联 软件服务
                                    9002649,//36.00 博彦科技  软件服务
                                    9002657,//37.00 中科金财  软件服务
                                    9002771,//38.00 真视通 软件服务
                                    9002777,//39.00 久远银海  软件服务
                                    300002,//40.00  神州泰岳  软件服务
                                    300010,//41.00  立思辰 软件服务
                                    300020,//42.00  银江股份  软件服务
                                    300033,//43.00  同花顺 软件服务
                                    300036,//44.00  超图软件  软件服务
                                    300044,//45.00  赛为智能  软件服务
                                    300047,//46.00  天源迪科  软件服务
                                    300050,//47.00  世纪鼎利  软件服务
                                    300065,//48.00  海兰信 软件服务
                                    300075,//49.00  数字政通  软件服务
                                    300085,//50.00  银之杰 软件服务
                                    300096,//51.00  易联众 软件服务
                                    300150,//52.00  世纪瑞尔  软件服务
                                    300166,//53.00  东方国信  软件服务
                                    300168,//54.00  万达信息  软件服务
                                    300170,//55.00  汉得信息  软件服务
                                    300182,//56.00  捷成股份  软件服务
                                    300183,//57.00  东软载波  软件服务
                                    300188,//58.00  美亚柏科  软件服务
                                    300209,//59.00  天泽信息  软件服务
                                    300212,//60.00  易华录 软件服务
                                    300229,//61.00  拓尔思 软件服务
                                    300231,//62.00  银信科技  软件服务
                                    300235,//63.00  方直科技  软件服务
                                    300245,//64.00  天玑科技  软件服务
                                    300248,//65.00  新开普 软件服务
                                    300253,//66.00  卫宁健康  软件服务
                                    300271,//67.00  华宇软件  软件服务
                                    300275,//68.00  梅安森 软件服务
                                    300277,//69.00  海联讯 软件服务
                                    300287,//70.00  飞利信 软件服务
                                    300288,//71.00  朗玛信息  软件服务
                                    300290,//72.00  荣科科技  软件服务
                                    300297,//73.00  蓝盾股份  软件服务
                                    300300,//74.00  汉鼎宇佑  软件服务
                                    300302,//75.00  同有科技  软件服务
                                    300311,//76.00  任子行 软件服务
                                    300324,//77.00  旋极信息  软件服务
                                    300330,//78.00  华虹计通  软件服务
                                    300339,//79.00  润和软件  软件服务
                                    300348,//80.00  长亮科技  软件服务
                                    300352,//81.00  北信源 软件服务
                                    300365,//82.00  恒华科技  软件服务
                                    300366,//83.00  创意信息  软件服务
                                    300369,//84.00  绿盟科技  软件服务
                                    300377,//85.00  赢时胜 软件服务
                                    300378,//86.00  鼎捷软件  软件服务
                                    300379,//87.00  东方通 软件服务
                                    300380,//88.00  安硕信息  软件服务
                                    300386,//89.00  飞天诚信  软件服务
                                    300419,//90.00  浩丰科技  软件服务
                                    300440,//91.00  运达科技  软件服务
                                    300448,//92.00  浩云科技  软件服务
                                    300451,//93.00  创业软件  软件服务
                                    300465,//94.00  高伟达 软件服务
                                    300468,//95.00  四方精创  软件服务
                                    300469,//96.00  信息发展  软件服务
                                    300479,//97.00  神思电子  软件服务
                                    300496,//98.00  中科创达  软件服务
                                    300508,//99.00  维宏股份  软件服务
                                    300513,//100.00 恒泰实达  软件服务
                                    300518,//101.00 盛讯达 软件服务
                                    300520,//102.00 科大国创  软件服务
                                    300523,//103.00 辰安科技  软件服务
                                    300525,//104.00 博思软件  软件服务
                                    300531,//105.00 优博讯 软件服务
                                    300532,//106.00 今天国际  软件服务
                                    300533,//107.00 冰川网络  软件服务
                                    300541,//108.00 先进数通  软件服务
                                    300542,//109.00 新晨科技  软件服务
                                    300550,//110.00 和仁科技  软件服务
                                    300552,//111.00 万集科技  软件服务
                                    300556,//112.00 丝路视觉  软件服务
                                    300559,//113.00 佳发安泰  软件服务
                                    300561,//114.00 汇金科技  软件服务
                                    300579,//115.00 数字认证  软件服务
                                    300588,//116.00 熙菱信息  软件服务
                                    300592,//117.00 华凯创意  软件服务
                                    600288,//118.00 大恒科技  软件服务
                                    600289,//119.00 亿阳信通  软件服务
                                    600406,//120.00 国电南瑞  软件服务
                                    600410,//121.00 华胜天成  软件服务
                                    600446,//122.00 金证股份  软件服务
                                    600455,//123.00 博通股份  软件服务
                                    600476,//124.00 湘邮科技  软件服务
                                    600536,//125.00 中国软件  软件服务
                                    600556,//126.00 ST慧球  软件服务
                                    600570,//127.00 恒生电子  软件服务
                                    600571,//128.00 信雅达 软件服务
                                    600588,//129.00 用友网络  软件服务
                                    600602,//130.00 云赛智联  软件服务
                                    600654,//131.00 中安消 软件服务
                                    600718,//132.00 东软集团  软件服务
                                    600728,//133.00 佳都科技  软件服务
                                    600756,//134.00 浪潮软件  软件服务
                                    600797,//135.00 浙大网新  软件服务
                                    600845,//136.00 宝信软件  软件服务
                                    600850,//137.00 华东电脑  软件服务
                                    601519,//138.00 大智慧 软件服务
                                    603189,//139.00 网达软件  软件服务
                                    603508,//140.00 思维列控  软件服务
                                    603528,//141.00 多伦科技  软件服务
                                    603636,//142.00 南威软件  软件服务
                                    603859,//143.00 能科股份  软件服务
                                    603918,//144.00 金桥信息  软件服务
                                    603990,//145.00 麦迪科技  软件服务
                                    603039//146.00 泛微网络  软件服务
    
    ));
    
    List<Integer> listCode_33_hlw = new ArrayList<Integer>(Arrays.asList(
                                    9000676,//1.00  智度股份  互联网
                                    9000681,//2.00  视觉中国  互联网
                                    9000971,//3.00  高升控股  互联网
                                    9002095,//4.00  生 意 宝 互联网
                                    9002113,//5.00  天润数娱  互联网
                                    9002123,//6.00  梦网荣信  互联网
                                    9002127,//7.00  南极电商  互联网
                                    9002131,//8.00  利欧股份  互联网
                                    9002148,//9.00  北纬通信  互联网
                                    9002174,//10.00 游族网络  互联网
                                    9002261,//11.00 拓维信息  互联网
                                    9002315,//12.00 焦点科技  互联网
                                    9002354,//13.00 天神娱乐  互联网
                                    9002464,//14.00 金利科技  互联网
                                    9002517,//15.00 恺英网络  互联网
                                    9002555,//16.00 三七互娱  互联网
                                    300051,//17.00  三五互联  互联网
                                    300052,//18.00  中青宝 互联网
                                    300059,//19.00  东方财富  互联网
                                    300104,//20.00  乐视网 互联网
                                    300113,//21.00  顺网科技  互联网
                                    300226,//22.00  上海钢联  互联网
                                    300242,//23.00  明家联合  互联网
                                    300295,//24.00  三六五网  互联网
                                    300315,//25.00  掌趣科技  互联网
                                    300343,//26.00  联创互联  互联网
                                    300392,//27.00  腾信股份  互联网
                                    300399,//28.00  京天利 互联网
                                    300418,//29.00  昆仑万维  互联网
                                    300431,//30.00  暴风集团  互联网
                                    300467,//31.00  迅游科技  互联网
                                    300494,//32.00  盛天网络  互联网
                                    300571,//33.00  平治信息  互联网
                                    600652,//34.00  游久游戏  互联网
                                    600986,//35.00  科达股份  互联网
                                    603000,//36.00  人民网 互联网
                                    603258,//37.00  电魂网络  互联网
                                    603888,//38.00  新华网 互联网
                                    603444//39.00  吉比特 互联网
    
    ));
    
    List<Integer> listCode_34_zhl = new ArrayList<Integer>(Arrays.asList(
                                  9000009,//1.00  中国宝安  综合类
                                  9000034,//2.00  神州数码  综合类
                                  9000301,//3.00  东方市场  综合类
                                  9000409,//4.00  山东地矿  综合类
                                  9000632,//5.00  三木集团  综合类
                                  9000633,//6.00  *ST合金 综合类
                                  9000701,//7.00  厦门信达  综合类
                                  9000839,//8.00  中信国安  综合类
                                  9000881,//9.00  大连国际  综合类
                                  9000909,//10.00 数源科技  综合类
                                  600051,//11.00  宁波联合  综合类
                                  600149,//12.00  廊坊发展  综合类
                                  600175,//13.00  美都能源  综合类
                                  600200,//14.00  江苏吴中  综合类
                                  600209,//15.00  罗顿发展  综合类
                                  600212,//16.00  *ST江泉 综合类
                                  600256,//17.00  广汇能源  综合类
                                  600603,//18.00  *ST兴业 综合类
                                  600614,//19.00  鼎立股份  综合类
                                  600620,//20.00  天宸股份  综合类
                                  600624,//21.00  复旦复华  综合类
                                  600647,//22.00  同达创业  综合类
                                  600701,//23.00  *ST工新 综合类
                                  600730,//24.00  中国高科  综合类
                                  600770,//25.00  综艺股份  综合类
                                  600777,//26.00  新潮能源  综合类
                                  600800,//27.00  天津磁卡  综合类
                                  600805,//28.00  悦达投资  综合类
                                  600811,//29.00  东方集团  综合类
                                  600846,//30.00  同济科技  综合类
                                  600892//31.00  大晟文化  综合类
    
    ));
    
    List<Integer> listCode_35_mtkc = new ArrayList<Integer>(Arrays.asList(
                                  9000552,//1.00  靖远煤电  煤炭开采
                                  9000571,//2.00  新大洲Ａ  煤炭开采
                                  9000780,//3.00  平庄能源  煤炭开采
                                  9000933,//4.00  *ST神火 煤炭开采
                                  9000937,//5.00  冀中能源  煤炭开采
                                  9000968,//6.00  *ST煤气 煤炭开采
                                  9000983,//7.00  西山煤电  煤炭开采
                                  9002128,//8.00  露天煤业  煤炭开采
                                  600121,//9.00 郑州煤电  煤炭开采
                                  600123,//10.00  兰花科创  煤炭开采
                                  600157,//11.00  永泰能源  煤炭开采
                                  600188,//12.00  兖州煤业  煤炭开采
                                  600348,//13.00  阳泉煤业  煤炭开采
                                  600395,//14.00  盘江股份  煤炭开采
                                  600397,//15.00  安源煤业  煤炭开采
                                  600403,//16.00  大有能源  煤炭开采
                                  600508,//17.00  上海能源  煤炭开采
                                  600546,//18.00  *ST山煤 煤炭开采
                                  600714,//19.00  金瑞矿业  煤炭开采
                                  600758,//20.00  红阳能源  煤炭开采
                                  600971,//21.00  恒源煤电  煤炭开采
                                  600997,//22.00  开滦股份  煤炭开采
                                  601001,//23.00  大同煤业  煤炭开采
                                  601088,//24.00  中国神华  煤炭开采
                                  601101,//25.00  昊华能源  煤炭开采
                                  601225,//26.00  陕西煤业  煤炭开采
                                  601666,//27.00  平煤股份  煤炭开采
                                  601699,//28.00  潞安环能  煤炭开采
                                  601898,//29.00  中煤能源  煤炭开采
                                  601918//30.00  *ST新集 煤炭开采
                                  
    ));
    
    List<Integer> listCode_36_jtjg = new ArrayList<Integer>(Arrays.asList(
                                  9000723,//1.00  美锦能源  焦炭加工
                                  600408,//2.00 安泰集团  焦炭加工
                                  600721,//3.00 *ST百花 焦炭加工
                                  600725,//4.00 *ST云维 焦炭加工
                                  600740,//5.00 山西焦化  焦炭加工
                                  600792,//6.00 云煤能源  焦炭加工
                                  601011,//7.00 宝泰隆 焦炭加工
                                  601015//8.00 陕西黑猫  焦炭加工
    
    ));
    
    List<Integer> listCode_37_slfd = new ArrayList<Integer>(Arrays.asList(
                                9000601,//1.00  韶能股份  水力发电
                                9000722,//2.00  湖南发展  水力发电
                                9000791,//3.00  甘肃电投  水力发电
                                9000993,//4.00  闽东电力  水力发电
                                9002039,//5.00  黔源电力  水力发电
                                600101,//6.00 明星电力  水力发电
                                600116,//7.00 三峡水利  水力发电
                                600131,//8.00 岷江水电  水力发电
                                600236,//9.00 桂冠电力  水力发电
                                600310,//10.00  桂东电力  水力发电
                                600452,//11.00  涪陵电力  水力发电
                                600505,//12.00  西昌电力  水力发电
                                600644,//13.00  乐山电力  水力发电
                                600674,//14.00  川投能源  水力发电
                                600868,//15.00  梅雁吉祥  水力发电
                                600886,//16.00  国投电力  水力发电
                                600900,//17.00  长江电力  水力发电
                                600969,//18.00  郴电国际  水力发电
                                600979,//19.00  广安爱众  水力发电
                                600995//20.00  文山电力  水力发电
    
    ));
    
    List<Integer> listCode_38_hldf = new ArrayList<Integer>(Arrays.asList(
                              9000027,//1.00  深圳能源  火力发电
                              9000037,//2.00  *ST南电A  火力发电
                              9000531,//3.00  穗恒运Ａ  火力发电
                              9000539,//4.00  粤电力Ａ  火力发电
                              9000543,//5.00  皖能电力  火力发电
                              9000600,//6.00  建投能源  火力发电
                              9000690,//7.00  宝新能源  火力发电
                              9000720,//8.00  新能泰山  火力发电
                              9000767,//9.00  漳泽电力  火力发电
                              9000875,//10.00 吉电股份  火力发电
                              9000883,//11.00 湖北能源  火力发电
                              9000899,//12.00 赣能股份  火力发电
                              9000958,//13.00 东方能源  火力发电
                              9000966,//14.00 长源电力  火力发电
                              9001896,//15.00 豫能控股  火力发电
                              9002479,//16.00 富春环保  火力发电
                              600011,//17.00  华能国际  火力发电
                              600021,//18.00  上海电力  火力发电
                              600023,//19.00  浙能电力  火力发电
                              600027,//20.00  华电国际  火力发电
                              600098,//21.00  广州发展  火力发电
                              600396,//22.00  金山股份  火力发电
                              600483,//23.00  福能股份  火力发电
                              600509,//24.00  天富能源  火力发电
                              600578,//25.00  京能电力  火力发电
                              600642,//26.00  申能股份  火力发电
                              600726,//27.00  华电能源  火力发电
                              600744,//28.00  华银电力  火力发电
                              600780,//29.00  通宝能源  火力发电
                              600795,//30.00  国电电力  火力发电
                              600863,//31.00  内蒙华电  火力发电
                              600864,//32.00  哈投股份  火力发电
                              601991//33.00  大唐发电  火力发电
    ));
    
    List<Integer> listCode_39_xxdl = new ArrayList<Integer>(Arrays.asList(
                            9000591,//1.00  太阳能 新型电力
                            9000862,//2.00  银星能源  新型电力
                            9000939,//3.00  凯迪生态  新型电力
                            600163,//4.00 中闽能源  新型电力
                            600277,//5.00 亿利洁能  新型电力
                            601016,//6.00 节能风电  新型电力
                            601985//7.00 中国核电  新型电力
    ));
    
    List<Integer> listCode_40_sykc = new ArrayList<Integer>(Arrays.asList(
                        9002207,//1.00  准油股份  石油开采
                        9002554,//2.00  惠博普 石油开采
                        9002629,//3.00  仁智股份  石油开采
                        9002828,//4.00  贝肯能源  石油开采
                        300084,//5.00 海默科技  石油开采
                        300157,//6.00 恒泰艾普  石油开采
                        300164,//7.00 通源石油  石油开采
                        300191,//8.00 潜能恒信  石油开采
                        600583,//9.00 海油工程  石油开采
                        600759,//10.00  洲际油气  石油开采
                        600871,//11.00  石化油服  石油开采
                        601808,//12.00  中海油服  石油开采
                        601857,//13.00  中国石油  石油开采
                        603727//14.00  博迈科 石油开采
    ));
    
    List<Integer> listCode_41_syjg = new ArrayList<Integer>(Arrays.asList(
                      9000059,//1.00  华锦股份  石油加工
                      9000637,//2.00  茂化实华  石油加工
                      9000819,//3.00  岳阳兴长  石油加工
                      9002377,//4.00  国创高新  石油加工
                      9002778,//5.00  高科石化  石油加工
                      600028,//6.00 中国石化  石油加工
                      600339,//7.00 *ST天利 石油加工
                      600688,//8.00 上海石化  石油加工
                      603798//9.00 康普顿 石油加工
    ));
    List<Integer> listCode_42_symy = new ArrayList<Integer>(Arrays.asList(
                      9000096,//1.00  广聚能源  石油贸易
                      9000159,//2.00  国际实业  石油贸易
                      9000554,//3.00  泰山石油  石油贸易
                      9002221,//4.00  东华能源  石油贸易
                      600387,//5.00 海越股份  石油贸易
                      603003//6.00 龙宇燃油  石油贸易
    ));
    
    List<Integer> listCode_43_pg = new ArrayList<Integer>(Arrays.asList(
                      9000629,//1.00  *ST钒钛 普钢
                      9000655,//2.00  金岭矿业  普钢
                      9000709,//3.00  河钢股份  普钢
                      9000898,//4.00  鞍钢股份  普钢
                      9000932,//5.00  华菱钢铁  普钢
                      9000959,//6.00  首钢股份  普钢
                      9002110,//7.00  三钢闽光  普钢
                      9002478,//8.00  常宝股份  普钢
                      600005,//9.00 武钢股份  普钢
                      600010,//10.00  包钢股份  普钢
                      600019,//11.00  宝钢股份  普钢
                      600022,//12.00  山东钢铁  普钢
                      600126,//13.00  杭钢股份  普钢
                      600231,//14.00  凌钢股份  普钢
                      600282,//15.00  南钢股份  普钢
                      600307,//16.00  酒钢宏兴  普钢
                      600532,//17.00  宏达矿业  普钢
                      600569,//18.00  安阳钢铁  普钢
                      600581,//19.00  *ST八钢 普钢
                      600608,//20.00  上海科技  普钢
                      600784,//21.00  鲁银投资  普钢
                      600808,//22.00  马钢股份  普钢
                      601003,//23.00  柳钢股份  普钢
                      601005,//24.00  重庆钢铁  普钢
                      601969//25.00  海南矿业  普钢
    
    ));
    
    List<Integer> listCode_44_tzg = new ArrayList<Integer>(Arrays.asList(
                    9000708,//1.00  大冶特钢  特种钢
                    9000825,//2.00  太钢不锈  特种钢
                    9002075,//3.00  沙钢股份  特种钢
                    9002318,//4.00  久立特材  特种钢
                    9002423,//5.00  *ST中特 特种钢
                    9002756,//6.00  永兴特钢  特种钢
                    600117,//7.00 西宁特钢  特种钢
                    600399,//8.00 抚顺特钢  特种钢
                    600507//9.00 方大特钢  特种钢
    ));
    
    List<Integer> listCode_45_gjg = new ArrayList<Integer>(Arrays.asList(
                    9000717,//1.00  *ST韶钢 钢加工
                    9000761,//2.00  本钢板材  钢加工
                    9000778,//3.00  新兴铸管  钢加工
                    9000890,//4.00  法 尔 胜 钢加工
                    9000969,//5.00  安泰科技  钢加工
                    9002132,//6.00  恒星科技  钢加工
                    9002352,//7.00  鼎泰新材  钢加工
                    9002359,//8.00  齐星铁塔  钢加工
                    9002443,//9.00  金洲管道  钢加工
                    9002487,//10.00 大金重工  钢加工
                    9002541,//11.00 鸿路钢构  钢加工
                    9002545,//12.00 东方铁塔  钢加工
                    9002743,//13.00 富煌钢构  钢加工
                    300345,//14.00  红宇新材  钢加工
                    9002843,//15.00 泰嘉股份  钢加工
                    600165,//16.00  新日恒力  钢加工
                    600477,//17.00  杭萧钢构  钢加工
                    600496,//18.00  精工钢构  钢加工
                    600558,//19.00  大西洋 钢加工
                    600782,//20.00  新钢股份  钢加工
                    600992,//21.00  贵绳股份  钢加工
                    601028,//22.00  玉龙股份  钢加工
                    603028,//23.00  赛福天 钢加工
                    603300,//24.00  华铁科技  钢加工
                    603577,//25.00  汇金通 钢加工
                    603878//26.00  武进不锈  钢加工
    
    ));
    
    List<Integer> listCode_46_t =new ArrayList<Integer>( Arrays.asList(
                          9000630,//1.00  铜陵有色  铜
                          9000878,//2.00  云南铜业  铜
                          9002171,//3.00  楚江新材  铜
                          9002203,//4.00  海亮股份  铜
                          9002295,//5.00  精艺股份  铜
                          600139,//6.00 西部资源  铜
                          600255,//7.00 鑫科材料  铜
                          600362,//8.00 江西铜业  铜
                          600490,//9.00 鹏欣资源  铜
                          601137,//10.00  博威合金  铜
                          601168//11.00  西部矿业  铜
    ));
    List<Integer> listCode_47_l = new ArrayList<Integer>(Arrays.asList(
                    9000612,//1.00  焦作万方  铝
                    9000807,//2.00  云铝股份  铝
                    9002082,//3.00  栋梁新材  铝
                    9002160,//4.00  常铝股份  铝
                    9002333,//5.00  罗普斯金  铝
                    9002379,//6.00  *ST鲁丰 铝
                    9002501,//7.00  利源精制  铝
                    9002540,//8.00  亚太科技  铝
                    9002578,//9.00  闽发铝业  铝
                    300328,//10.00  宜安科技  铝
                    300337,//11.00  银邦股份  铝
                    300428,//12.00  四通新材  铝
                    300489,//13.00  中飞股份  铝
                    9002824,//14.00  和胜股份  铝
                    600219,//15.00  南山铝业  铝
                    600595,//16.00  中孚实业  铝
                    600673,//17.00  东阳光科  铝
                    600768,//18.00  宁波富邦  铝
                    600888,//19.00  新疆众和  铝
                    601388,//20.00  怡球资源  铝
                    601600,//21.00  中国铝业  铝
                    601677//22.00  明泰铝业  铝
    ));
    List<Integer> listCode_47_yx = new ArrayList<Integer>(Arrays.asList(
                9000060,//1.00  中金岭南  铅锌
                9000426,//2.00  兴业矿业  铅锌
                9000603,//3.00  盛达矿业  铅锌
                9000688,//4.00  建新矿业  铅锌
                9000751,//5.00  锌业股份  铅锌
                9000758,//6.00  中色股份  铅锌
                9000975,//7.00  银泰资源  铅锌
                9002114,//8.00  罗平锌电  铅锌
                600331,//9.00 宏达股份  铅锌
                600338,//10.00  西藏珠峰  铅锌
                600497,//11.00  驰宏锌锗  铅锌
                600531,//12.00  豫光金铅  铅锌
                600961,//13.00  株冶集团  铅锌
                601020//14.00  华钰矿业  铅锌
    
    ));
    
    List<Integer> listCode_48_hj = new ArrayList<Integer>(Arrays.asList(
                9002155,//1.00  湖南黄金  黄金
                9002237,//2.00  恒邦股份  黄金
                600311,//3.00 荣华实业  黄金
                600385,//4.00 山东金泰  黄金
                600489,//5.00 中金黄金  黄金
                600547,//6.00 山东黄金  黄金
                600687,//7.00 刚泰控股  黄金
                600766,//8.00 园城黄金  黄金
                600988,//9.00 赤峰黄金  黄金
                601069,//10.00  西部黄金  黄金
                601899//11.00  紫金矿业  黄金
    
    ));
    
    List<Integer> listCode_49_xjs = new ArrayList<Integer>(Arrays.asList(
                  9000657,//1.00  中钨高新  小金属
                  9000693,//2.00  ST华泽  小金属
                  9000697,//3.00  炼石有色  小金属
                  9000762,//4.00  西藏矿业  小金属
                  9000831,//5.00  *ST五稀 小金属
                  9000960,//6.00  锡业股份  小金属
                  9000962,//7.00  *ST东钽 小金属
                  9002149,//8.00  西部材料  小金属
                  9002167,//9.00  东方锆业  小金属
                  9002182,//10.00 云海金属  小金属
                  9002340,//11.00 格林美 小金属
                  9002378,//12.00 章源钨业  小金属
                  9002428,//13.00 云南锗业  小金属
                  9002460,//14.00 赣锋锂业  小金属
                  9002466,//15.00 天齐锂业  小金属
                  9002716,//16.00 金贵银业  小金属
                  300034,//17.00  钢研高纳  小金属
                  9002842,//18.00  翔鹭钨业  小金属
                  600111,//19.00  北方稀土  小金属
                  600259,//20.00  广晟有色  小金属
                  600390,//21.00  *ST金瑞 小金属
                  600392,//22.00  盛和资源  小金属
                  600432,//23.00  *ST吉恩 小金属
                  600456,//24.00  宝钛股份  小金属
                  600459,//25.00  贵研铂业  小金属
                  600549,//26.00  厦门钨业  小金属
                  600615,//27.00  丰华股份  小金属
                  600711,//28.00  盛屯矿业  小金属
                  601958,//29.00  金钼股份  小金属
                  603399,//30.00  新华龙 小金属
                  603799,//31.00  华友钴业  小金属
                  603993,//32.00  洛阳钼业  小金属
                  601212//33.00  白银有色  小金属
    ));
    
    List<Integer> listCode_50_hgyl = new ArrayList<Integer>(Arrays.asList(
                9000510,//1.00  金路集团  化工原料
                9000545,//2.00  金浦钛业  化工原料
                9000635,//3.00  英 力 特 化工原料
                9000683,//4.00  远兴能源  化工原料
                9000698,//5.00  沈阳化工  化工原料
                9000707,//6.00  双环科技  化工原料
                9000755,//7.00  山西三维  化工原料
                9000818,//8.00  方大化工  化工原料
                9000822,//9.00  山东海化  化工原料
                9000985,//10.00 大庆华科  化工原料
                9000990,//11.00 诚志股份  化工原料
                9002002,//12.00 鸿达兴业  化工原料
                9002037,//13.00 久联发展  化工原料
                9002054,//14.00 德美化工  化工原料
                9002061,//15.00 *ST江化 化工原料
                9002068,//16.00 黑猫股份  化工原料
                9002092,//17.00 中泰化学  化工原料
                9002096,//18.00 南岭民爆  化工原料
                9002109,//19.00 *ST兴化 化工原料
                9002125,//20.00 湘潭电化  化工原料
                9002136,//21.00 安 纳 达 化工原料
                9002145,//22.00 中核钛白  化工原料
                9002165,//23.00 红 宝 丽 化工原料
                9002226,//24.00 江南化工  化工原料
                9002246,//25.00 北化股份  化工原料
                9002250,//26.00 联化科技  化工原料
                9002326,//27.00 永太科技  化工原料
                9002341,//28.00 新纶科技  化工原料
                9002360,//29.00 同德化工  化工原料
                9002361,//30.00 神剑股份  化工原料
                9002386,//31.00 天原集团  化工原料
                9002407,//32.00 多氟多 化工原料
                9002408,//33.00 齐翔腾达  化工原料
                9002409,//34.00 雅克科技  化工原料
                9002442,//35.00 龙星化工  化工原料
                9002450,//36.00 康得新 化工原料
                9002453,//37.00 天马精化  化工原料
                9002455,//38.00 百川股份  化工原料
                9002476,//39.00 宝莫股份  化工原料
                9002497,//40.00 雅化集团  化工原料
                9002562,//41.00 兄弟科技  化工原料
                9002584,//42.00 西陇科学  化工原料
                9002591,//43.00 恒大高新  化工原料
                9002597,//44.00 金禾实业  化工原料
                9002601,//45.00 佰利联 化工原料
                9002632,//46.00 道明光学  化工原料
                9002643,//47.00 万润股份  化工原料
                9002648,//48.00 卫星石化  化工原料
                9002666,//49.00 德联集团  化工原料
                9002669,//50.00 康达新材  化工原料
                9002683,//51.00 宏大爆破  化工原料
                9002709,//52.00 天赐材料  化工原料
                9002741,//53.00 光华科技  化工原料
                9002748,//54.00 世龙实业  化工原料
                9002753,//55.00 永东股份  化工原料
                9002783,//56.00 凯龙股份  化工原料
                9002802,//57.00 洪汇新材  化工原料
                9002805,//58.00 丰元股份  化工原料
                9002809,//59.00 红墙股份  化工原料
                9002810,//60.00 山东赫达  化工原料
                9002825,//61.00 纳尔股份  化工原料
                9002827,//62.00 高争民爆  化工原料
                300019,//63.00  硅宝科技  化工原料
                300037,//64.00  新宙邦 化工原料
                300041,//65.00  回天新材  化工原料
                300054,//66.00  鼎龙股份  化工原料
                300082,//67.00  奥克股份  化工原料
                300107,//68.00  建新股份  化工原料
                300109,//69.00  新开源 化工原料
                300121,//70.00  阳谷华泰  化工原料
                300132,//71.00  青松股份  化工原料
                300135,//72.00  宝利国际  化工原料
                300174,//73.00  元力股份  化工原料
                300200,//74.00  高盟新材  化工原料
                300214,//75.00  日科化学  化工原料
                300243,//76.00  瑞丰高材  化工原料
                300387,//77.00  富邦股份  化工原料
                300405,//78.00  科隆精化  化工原料
                300429,//79.00  强力新材  化工原料
                300437,//80.00  清水源 化工原料
                300446,//81.00  乐凯新材  化工原料
                300459,//82.00  金科娱乐  化工原料
                300481,//83.00  濮阳惠成  化工原料
                300487,//84.00  蓝晓科技  化工原料
                300505,//85.00  川金诺 化工原料
                300530,//86.00  达志科技  化工原料
                300535,//87.00  达威股份  化工原料
                300568,//88.00  星源材质  化工原料
                300586,//89.00  美联新材  化工原料
                300596,//90.00  利安隆 化工原料
                600075,//91.00  新疆天业  化工原料
                600078,//92.00  澄星股份  化工原料
                600091,//93.00  ST明科  化工原料
                600135,//94.00  乐凯胶片  化工原料
                600141,//95.00  兴发集团  化工原料
                600160,//96.00  巨化股份  化工原料
                600228,//97.00  昌九生化  化工原料
                600273,//98.00  嘉化能源  化工原料
                600281,//99.00  太化股份  化工原料
                600301,//100.00 *ST南化 化工原料
                600309,//101.00 万华化学  化工原料
                600319,//102.00 *ST亚星 化工原料
                600328,//103.00 兰太实业  化工原料
                600367,//104.00 红星发展  化工原料
                600378,//105.00 天科股份  化工原料
                600409,//106.00 三友化工  化工原料
                600618,//107.00 氯碱化工  化工原料
                600636,//108.00 三爱富 化工原料
                600722,//109.00 金牛化工  化工原料
                600746,//110.00 江苏索普  化工原料
                600844,//111.00 丹化科技  化工原料
                600985,//112.00 雷鸣科化  化工原料
                601208,//113.00 东材科技  化工原料
                601216,//114.00 君正集团  化工原料
                601678,//115.00 滨化股份  化工原料
                603002,//116.00 宏昌电子  化工原料
                603010,//117.00 万盛股份  化工原料
                603026,//118.00 石大胜华  化工原料
                603067,//119.00 振华股份  化工原料
                603077,//120.00 和邦生物  化工原料
                603227,//121.00 雪峰科技  化工原料
                603299,//122.00 井神股份  化工原料
                603585,//123.00 苏利股份  化工原料
                603928,//124.00 兴业股份  化工原料
                603968,//125.00 醋化股份  化工原料
                603977//126.00 国泰集团  化工原料
    
    ));
    
    List<Integer> listCode_51_lyhf = new ArrayList<Integer>(Arrays.asList(
                9000422,//1.00  湖北宜化  农药化肥
                9000525,//2.00  红 太 阳 农药化肥
                9000553,//3.00  沙隆达Ａ  农药化肥
                9000731,//4.00  四川美丰  农药化肥
                9000792,//5.00  盐湖股份  农药化肥
                9000830,//6.00  鲁西化工  农药化肥
                9000902,//7.00  新洋丰 农药化肥
                9000912,//8.00  泸天化 农药化肥
                9000950,//9.00  *ST建峰 农药化肥
                9000953,//10.00 河池化工  农药化肥
                9002018,//11.00 华信国际  农药化肥
                9002170,//12.00 芭田股份  农药化肥
                9002215,//13.00 诺 普 信 农药化肥
                9002258,//14.00 利尔化学  农药化肥
                9002274,//15.00 华昌化工  农药化肥
                9002391,//16.00 长青股份  农药化肥
                9002470,//17.00 金正大 农药化肥
                9002496,//18.00 辉丰股份  农药化肥
                9002513,//19.00 *ST蓝丰 农药化肥
                9002538,//20.00 司尔特 农药化肥
                9002539,//21.00 云图控股  农药化肥
                9002588,//22.00 史丹利 农药化肥
                9002734,//23.00 利民股份  农药化肥
                9002749,//24.00 国光股份  农药化肥
                300261,//25.00  雅本化学  农药化肥
                300575,//26.00  中旗股份  农药化肥
                9000155,//27.00 *ST川化 农药化肥
                600096,//28.00  云天化 农药化肥
                600226,//29.00  升华拜克  农药化肥
                600227,//30.00  赤天化 农药化肥
                600230,//31.00  *ST沧大 农药化肥
                600389,//32.00  江山股份  农药化肥
                600423,//33.00  柳化股份  农药化肥
                600426,//34.00  华鲁恒升  农药化肥
                600470,//35.00  六国化工  农药化肥
                600486,//36.00  扬农化工  农药化肥
                600538,//37.00  国发股份  农药化肥
                600596,//38.00  新安股份  农药化肥
                600691,//39.00  阳煤化工  农药化肥
                600727,//40.00  鲁北化工  农药化肥
                600731,//41.00  湖南海利  农药化肥
                600796,//42.00  钱江生化  农药化肥
                600803,//43.00  新奥股份  农药化肥
                603599,//44.00  广信股份  农药化肥
                603639//45.00  海利尔 农药化肥
    ));
    
    List<Integer> listCode_52_sl = new ArrayList<Integer>(Arrays.asList(
              9000859,//1.00  国风塑业  塑料
              9000973,//2.00  佛塑科技  塑料
              9002014,//3.00  永新股份  塑料
              9002108,//4.00  沧州明珠  塑料
              9002243,//5.00  通产丽星  塑料
              9002263,//6.00  大 东 南 塑料
              9002324,//7.00  普利特 塑料
              9002395,//8.00  双象股份  塑料
              9002420,//9.00  毅昌股份  塑料
              9002457,//10.00 青龙管业  塑料
              9002522,//11.00 浙江众成  塑料
              9002585,//12.00 双星新材  塑料
              9002641,//13.00 永高股份  塑料
              9002676,//14.00 顺威股份  塑料
              9002694,//15.00 顾地科技  塑料
              9002735,//16.00 王子新材  塑料
              9002768,//17.00 国恩股份  塑料
              300169,//18.00  天晟新材  塑料
              300198,//19.00  纳川股份  塑料
              300218,//20.00  安利股份  塑料
              300221,//21.00  银禧科技  塑料
              300230,//22.00  永利股份  塑料
              300305,//23.00  裕兴股份  塑料
              300321,//24.00  同大股份  塑料
              300325,//25.00  德威新材  塑料
              300393,//26.00  中来股份  塑料
              300539,//27.00  横河模具  塑料
              9002838,//28.00  道恩股份  塑料
              600143,//29.00  金发科技  塑料
              600146,//30.00  商赢环球  塑料
              600444,//31.00  国机通用  塑料
              600458,//32.00  时代新材  塑料
              600589,//33.00  广东榕泰  塑料
              603806,//34.00  福斯特 塑料
              603266//35.00  天龙股份  塑料
              
    ));
    
    List<Integer> listCode_53_xj = new ArrayList<Integer>(Arrays.asList(
            9000887,//1.00  中鼎股份  橡胶
            9002211,//2.00  宏达新材  橡胶
            9002224,//3.00  三 力 士 橡胶
            9002381,//4.00  双箭股份  橡胶
            300031,//5.00 宝通科技  橡胶
            300320,//6.00 海达股份  橡胶
            300478,//7.00 杭州高新  橡胶
            300547,//8.00 川环科技  橡胶
            300587,//9.00 天铁股份  橡胶
            601118,//10.00  海南橡胶  橡胶
            603033//11.00  三维股份  橡胶
    ));
    
    List<Integer> listCode_54_yltl = new ArrayList<Integer>(Arrays.asList(
              
              9000565,//1.00  渝三峡Ａ  染料涂料
              9002010,//2.00  传化智联  染料涂料
              9002256,//3.00  兆新股份  染料涂料
              9002319,//4.00  乐通股份  染料涂料
              9002440,//5.00  闰土股份  染料涂料
              300063,//6.00 天龙集团  染料涂料
              300067,//7.00 安诺其 染料涂料
              300192,//8.00 科斯伍德  染料涂料
              300225,//9.00 金力泰 染料涂料
              300236,//10.00  上海新阳  染料涂料
              300398,//11.00  飞凯材料  染料涂料
              300522,//12.00  世名科技  染料涂料
              300537,//13.00  广信材料  染料涂料
              300576,//14.00  容大感光  染料涂料
              600352,//15.00  浙江龙盛  染料涂料
              603188,//16.00  亚邦股份  染料涂料
              603737,//17.00  三棵树 染料涂料
              603823//18.00  百合花 染料涂料
    
    ));
    
    List<Integer> listCode_55_tc = new ArrayList<Integer>(Arrays.asList(
            300089,//1.00 文化长城  陶瓷
            300234,//2.00 开尔新材  陶瓷
            300285,//3.00 国瓷材料  陶瓷
            300409,//4.00 道氏技术  陶瓷
            600145,//5.00 *ST新亿 陶瓷
            603268,//6.00 松发股份  陶瓷
            603838//7.00 四通股份  陶瓷

    ));
    
    
    List<Integer> listCode_56_sn = new ArrayList<Integer>(Arrays.asList(
              9000401,//1.00  冀东水泥  水泥
              9000546,//2.00  金圆股份  水泥
              9000672,//3.00  上峰水泥  水泥
              9000789,//4.00  万年青 水泥
              9000877,//5.00  天山股份  水泥
              9000885,//6.00  同力水泥  水泥
              9000935,//7.00  四川双马  水泥
              9002233,//8.00  塔牌集团  水泥
              9002302,//9.00  西部建设  水泥
              9002619,//10.00 巨龙管业  水泥
              600425,//11.00  青松建化  水泥
              600449,//12.00  宁夏建材  水泥
              600539,//13.00  ST狮头  水泥
              600585,//14.00  海螺水泥  水泥
              600668,//15.00  尖峰集团  水泥
              600678,//16.00  四川金顶  水泥
              600720,//17.00  祁连山 水泥
              600801,//18.00  华新水泥  水泥
              600802,//19.00  福建水泥  水泥
              600881,//20.00  亚泰集团  水泥
              600883,//21.00  博闻科技  水泥
              601992//22.00  金隅股份  水泥

    ));
    
    List<Integer> listCode_57_bl = new ArrayList<Integer>(Arrays.asList(
    9000012,//1.00  南 玻Ａ  玻璃
    9002201,//2.00  九鼎新材  玻璃
    9002571,//3.00  德力股份  玻璃
    9002623,//4.00  亚玛顿 玻璃
    300093,//5.00 金刚玻璃  玻璃
    300160,//6.00 秀强股份  玻璃
    300196,//7.00 长海股份  玻璃
    300395,//8.00 菲利华 玻璃
    600176,//9.00 中国巨石  玻璃
    600293,//10.00  三峡新材  玻璃
    600529,//11.00  山东药玻  玻璃
    600552,//12.00  凯盛科技  玻璃
    600586,//13.00  金晶科技  玻璃
    600819,//14.00  耀皮玻璃  玻璃
    600876,//15.00  洛阳玻璃  玻璃
    601636,//16.00  旗滨集团  玻璃
    603021,//17.00  山东华鹏  玻璃
    603601//18.00  再升科技  玻璃
    ));
    
    List<Integer> listCode_58_qtjc = new ArrayList<Integer>(Arrays.asList(

      9000023,//1.00  深天地Ａ  其他建材
      9000055,//2.00  方大集团  其他建材
      9000509,//3.00  华塑控股  其他建材
      9000619,//4.00  海螺型材  其他建材
      9000786,//5.00  北新建材  其他建材
      9002043,//6.00  兔 宝 宝 其他建材
      9002066,//7.00  瑞泰科技  其他建材
      9002205,//8.00  国统股份  其他建材
      9002225,//9.00  濮耐股份  其他建材
      9002271,//10.00 东方雨虹  其他建材
      9002372,//11.00 伟星新材  其他建材
      9002392,//12.00 北京利尔  其他建材
      9002398,//13.00 建研集团  其他建材
      9002596,//14.00 海南瑞泽  其他建材
      9002652,//15.00 扬子新材  其他建材
      9002671,//16.00 龙泉股份  其他建材
      9002742,//17.00 三圣股份  其他建材
      9002785,//18.00 万里石 其他建材
      9002791,//19.00 坚朗五金  其他建材
      300163,//20.00  先锋新材  其他建材
      300344,//21.00  太空板业  其他建材
      300374,//22.00  恒通科技  其他建材
      600076,//23.00  康欣新材  其他建材
      600155,//24.00  宝硕股份  其他建材
      600321,//25.00  国栋建设  其他建材
      600634,//26.00  中技控股  其他建材
      603616,//27.00  韩建河山  其他建材
      603969//28.00  银龙股份  其他建材

    ));
    
    List<Integer> listCode_59_zzy = new ArrayList<Integer>(Arrays.asList(

        9000713,//1.00  丰乐种业  种植业
        9000998,//2.00  隆平高科  种植业
        9002041,//3.00  登海种业  种植业
        9002772,//4.00  众兴菌业  种植业
        300087,//5.00 荃银高科  种植业
        300143,//6.00 星河生物  种植业
        300189,//7.00 神农基因  种植业
        300511,//8.00 雪榕生物  种植业
        600313,//9.00 农发种业  种植业
        600354,//10.00  敦煌种业  种植业
        600371,//11.00  万向德农  种植业
        600506,//12.00  香梨股份  种植业
        600540,//13.00  新赛股份  种植业
        600598//14.00  北大荒 种植业
    ));
    
    List<Integer> listCode_60_yy = new ArrayList<Integer>(Arrays.asList(
          9000798,//1.00  中水渔业  渔业
          9002069,//2.00  *ST獐岛 渔业
          9002086,//3.00  东方海洋  渔业
          9002447,//4.00  壹桥股份  渔业
          9002696,//5.00  百洋股份  渔业
          300094,//6.00 国联水产  渔业
          600097,//7.00 开创国际  渔业
          600257,//8.00 大湖股份  渔业
          600467//9.00 好当家 渔业
    ));
    List<Integer> listCode_61_ly = new ArrayList<Integer>(Arrays.asList(
      9000592,//1.00  平潭发展  林业
      9000663,//2.00  永安林业  林业
      9002679,//3.00  福建金森  林业
      600189,//4.00 吉林森工  林业
      600265,//5.00 *ST景谷 林业
      601996//6.00 丰林集团  林业
    ));
    List<Integer> listCode_62_sl = new ArrayList<Integer>(Arrays.asList(
        9000048,//1.00  康达尔 饲料
        9000702,//2.00  正虹科技  饲料
        9000876,//3.00  新 希 望 饲料
        9002100,//4.00  天康生物  饲料
        9002124,//5.00  天邦股份  饲料
        9002157,//6.00  正邦科技  饲料
        9002311,//7.00  海大集团  饲料
        9002385,//8.00  大北农 饲料
        9002548,//9.00  金新农 饲料
        9002567,//10.00 唐人神 饲料
        300381,//11.00  溢多利 饲料
        600195,//12.00  中牧股份  饲料
        600438,//13.00  通威股份  饲料
        603609//14.00  禾丰牧业  饲料
    
    ));
    List<Integer> listCode_63_lyzh = new ArrayList<Integer>(Arrays.asList(
    9000061,//1.00  农 产 品 农业综合
    9000735,//2.00  罗 牛 山 农业综合
    9000930,//3.00  中粮生化  农业综合
    9002173,//4.00  *ST创疗 农业综合
    9002234,//5.00  民和股份  农业综合
    9002299,//6.00  圣农发展  农业综合
    9002321,//7.00  华英农业  农业综合
    9002458,//8.00  益生股份  农业综合
    9002477,//9.00  雏鹰农牧  农业综合
    9002505,//10.00 大康农业  农业综合
    9002714,//11.00 牧原股份  农业综合
    9002746,//12.00 仙坛股份  农业综合
    300021,//13.00  大禹节水  农业综合
    300106,//14.00  西部牧业  农业综合
    300268,//15.00  万福生科  农业综合
    300313,//16.00  天山生物  农业综合
    300498,//17.00  温氏股份  农业综合
    600108,//18.00  亚盛集团  农业综合
    600127,//19.00  金健米业  农业综合
    600251,//20.00  冠农股份  农业综合
    600275,//21.00  武昌鱼 农业综合
    600359,//22.00  新农开发  农业综合
    600965,//23.00  福成股份  农业综合
    600975,//24.00  新五丰 农业综合
    603336,//25.00  宏辉果蔬  农业综合
    603668//26.00  天马科技  农业综合
    ));
    
    List<Integer> listCode_64_fz = new ArrayList<Integer>(Arrays.asList(
    9000045,//1.00  深纺织Ａ  纺织
    9000158,//2.00  常山股份  纺织
    9000611,//3.00  *ST天首 纺织
    9000726,//4.00  鲁 泰Ａ  纺织
    9000779,//5.00  三毛派神  纺织
    9000803,//6.00  金宇车城  纺织
    9000850,//7.00  华茂股份  纺织
    9000955,//8.00  欣龙控股  纺织
    9000982,//9.00  中银绒业  纺织
    9002034,//10.00 美 欣 达 纺织
    9002042,//11.00 华孚色纺  纺织
    9002070,//12.00 众和股份  纺织
    9002072,//13.00 凯瑞德 纺织
    9002083,//14.00 孚日股份  纺织
    9002087,//15.00 新野纺织  纺织
    9002144,//16.00 宏达高科  纺织
    9002193,//17.00 山东如意  纺织
    9002293,//18.00 罗莱生活  纺织
    9002327,//19.00 富安娜 纺织
    9002394,//20.00 联发股份  纺织
    9002397,//21.00 梦洁股份  纺织
    9002404,//22.00 嘉欣丝绸  纺织
    9002516,//23.00 旷达科技  纺织
    9002674,//24.00 兴业科技  纺织
    9002761,//25.00 多喜爱 纺织
    300577,//26.00  开润股份  纺织
    600070,//27.00  浙江富润  纺织
    600152,//28.00  维科精华  纺织
    600156,//29.00  华升股份  纺织
    600220,//30.00  江苏阳光  纺织
    600232,//31.00  金鹰股份  纺织
    600370,//32.00  三房巷 纺织
    600448,//33.00  华纺股份  纺织
    600493,//34.00  凤竹纺织  纺织
    600626,//35.00  申达股份  纺织
    600630,//36.00  龙头股份  纺织
    600689,//37.00  上海三毛  纺织
    600851,//38.00  海欣股份  纺织
    600987,//39.00  航民股份  纺织
    601339,//40.00  百隆东方  纺织
    603558,//41.00  健盛集团  纺织
    603889//42.00  新澳股份  纺织
    ));
    
    List<Integer> listCode_65_fs = new ArrayList<Integer>(Arrays.asList(

        9002003,//1.00  伟星股份  服饰
        9002029,//2.00  七 匹 狼 服饰
        9002098,//3.00  浔兴股份  服饰
        9002154,//4.00  报 喜 鸟 服饰
        9002269,//5.00  美邦服饰  服饰
        9002291,//6.00  星期六 服饰
        9002345,//7.00  潮宏基 服饰
        9002356,//8.00  赫美集团  服饰
        9002425,//9.00  凯撒文化  服饰
        9002485,//10.00 希努尔 服饰
        9002486,//11.00 嘉麟杰 服饰
        9002494,//12.00 华斯股份  服饰
        9002503,//13.00 搜于特 服饰
        9002563,//14.00 森马服饰  服饰
        9002569,//15.00 步森股份  服饰
        9002574,//16.00 明牌珠宝  服饰
        9002612,//17.00 朗姿股份  服饰
        9002634,//18.00 棒杰股份  服饰
        9002640,//19.00 跨境通 服饰
        9002656,//20.00 摩登大道  服饰
        9002687,//21.00 乔治白 服饰
        9002699,//22.00 美盛文化  服饰
        9002721,//23.00 金一文化  服饰
        9002731,//24.00 萃华珠宝  服饰
        9002740,//25.00 爱迪尔 服饰
        9002762,//26.00 金发拉比  服饰
        9002763,//27.00 汇洁股份  服饰
        9002776,//28.00 柏堡龙 服饰
        9002832,//29.00 比音勒芬  服饰
        300005,//30.00  探路者 服饰
        300591,//31.00  万里马 服饰
        600086,//32.00  东方金钰  服饰
        600107,//33.00  美尔雅 服饰
        600137,//34.00  浪莎股份  服饰
        600177,//35.00  雅戈尔 服饰
        600272,//36.00  开开实业  服饰
        600295,//37.00  鄂尔多斯  服饰
        600398,//38.00  海澜之家  服饰
        600400,//39.00  红豆股份  服饰
        600439,//40.00  瑞贝卡 服饰
        600612,//41.00  老凤祥 服饰
        600884,//42.00  杉杉股份  服饰
        601566,//43.00  九牧王 服饰
        601718,//44.00  际华集团  服饰
        603001,//45.00  奥康国际  服饰
        603116,//46.00  红蜻蜓 服饰
        603518,//47.00  维格娜丝  服饰
        603555,//48.00  贵人鸟 服饰
        603608,//49.00  天创时尚  服饰
        603808,//50.00  歌力思 服饰
        603900,//51.00  通灵珠宝  服饰
        603958,//52.00  哈森股份  服饰
        603877//53.00  太平鸟 服饰
    ));
    
    List<Integer> listCode_66_rzp = new ArrayList<Integer>(Arrays.asList(
        9002329,//1.00  皇氏集团  乳制品
        9002570,//2.00  贝因美 乳制品
        9002719,//3.00  麦趣尔 乳制品
        9002732,//4.00  燕塘乳业  乳制品
        9002770,//5.00  科迪乳业  乳制品
        600419,//6.00 天润乳业  乳制品
        600429,//7.00 三元股份  乳制品
        600597,//8.00 光明乳业  乳制品
        600882,//9.00 广泽股份  乳制品
        600887//10.00  伊利股份  乳制品
    ));
    
    List<Integer> listCode_67_ryl =new ArrayList<Integer>( Arrays.asList(
        9000019,//1.00  深深宝Ａ  软饮料
        9000848,//2.00  承德露露  软饮料
        9002387,//3.00  黑牛食品  软饮料
        600300,//4.00 维维股份  软饮料
        600962//5.00 国投中鲁  软饮料
    ));
    List<Integer> listCode_68_sp = new ArrayList<Integer>(Arrays.asList(
        9000529,//1.00  广弘控股  食品
        9000639,//2.00  西王食品  食品
        9000716,//3.00  黑芝麻 食品
        9000893,//4.00  东凌国际  食品
        9000895,//5.00  双汇发展  食品
        9000911,//6.00  南宁糖业  食品
        9000972,//7.00  中基健康  食品
        9002053,//8.00  云南能投  食品
        9002216,//9.00  三全食品  食品
        9002220,//10.00 天宝股份  食品
        9002286,//11.00 保龄宝 食品
        9002330,//12.00 得利斯 食品
        9002481,//13.00 双塔食品  食品
        9002495,//14.00 佳隆股份  食品
        9002507,//15.00 涪陵榨菜  食品
        9002515,//16.00 金字火腿  食品
        9002557,//17.00 洽洽食品  食品
        9002582,//18.00 好想你 食品
        9002604,//19.00 龙力生物  食品
        9002626,//20.00 金达威 食品
        9002650,//21.00 加加食品  食品
        9002661,//22.00 克明面业  食品
        9002695,//23.00 煌上煌 食品
        9002702,//24.00 海欣食品  食品
        9002726,//25.00 龙大肉食  食品
        9002820,//26.00 桂发祥 食品
        300138,//27.00  晨光生物  食品
        300146,//28.00  汤臣倍健  食品
        300149,//29.00  量子高科  食品
        300175,//30.00  朗源股份  食品
        300401,//31.00  花园生物  食品
        9002840,//32.00 华统股份  食品
        600073,//33.00  上海梅林  食品
        600186,//34.00  莲花健康  食品
        600191,//35.00  华资实业  食品
        600298,//36.00  安琪酵母  食品
        600305,//37.00  恒顺醋业  食品
        600737,//38.00  中粮屯河  食品
        600866,//39.00  *ST星湖 食品
        600872,//40.00  中炬高新  食品
        600873,//41.00  梅花生物  食品
        603020,//42.00  爱普股份  食品
        603027,//43.00  千禾味业  食品
        603288,//44.00  海天味业  食品
        603696,//45.00  安记食品  食品
        603866,//46.00  桃李面包  食品
        603886//47.00  元祖股份  食品
    ));
    
    List<Integer> listCode_69_bj = new ArrayList<Integer>(Arrays.asList(
    
    9000568,//1.00  泸州老窖  白酒
    9000596,//2.00  古井贡酒  白酒
    9000799,//3.00  酒鬼酒 白酒
    9000858,//4.00  五 粮 液 白酒
    9000860,//5.00  顺鑫农业  白酒
    9000995,//6.00  *ST皇台 白酒
    9002304,//7.00  洋河股份  白酒
    9002646,//8.00  青青稞酒  白酒
    600197,//9.00 伊力特 白酒
    600199,//10.00  金种子酒  白酒
    600519,//11.00  贵州茅台  白酒
    600559,//12.00  老白干酒  白酒
    600702,//13.00  沱牌舍得  白酒
    600779,//14.00  水井坊 白酒
    600809,//15.00  山西汾酒  白酒
    603198,//16.00  迎驾贡酒  白酒
    603369,//17.00  今世缘 白酒
    603589,//18.00  口子窖 白酒
    603919//19.00  金徽酒 白酒
    
    ));
    List<Integer> listCode_70_pj = new ArrayList<Integer>(Arrays.asList(

        9000729,//1.00  燕京啤酒  啤酒
        9000752,//2.00  西藏发展  啤酒
        9000929,//3.00  兰州黄河  啤酒
        9002461,//4.00  珠江啤酒  啤酒
        600132,//5.00 重庆啤酒  啤酒
        600573,//6.00 惠泉啤酒  啤酒
        600600//7.00 青岛啤酒  啤酒
    ));
    
    List<Integer> listCode_71_hhyj =new ArrayList<Integer>( Arrays.asList(
    9000557,//1.00  西部创业  红黄药酒
    9000869,//2.00  张 裕Ａ  红黄药酒
    9002568,//3.00  百润股份  红黄药酒
    600059,//4.00 古越龙山  红黄药酒
    600084,//5.00 中葡股份  红黄药酒
    600238,//6.00 海南椰岛  红黄药酒
    600365,//7.00 通葡股份  红黄药酒
    600543,//8.00 莫高股份  红黄药酒
    600616,//9.00 金枫酒业  红黄药酒
    601579,//10.00  会稽山 红黄药酒
    603779//11.00  威龙股份  红黄药酒
    
    ));
    List<Integer> listCode_72_qczc = new ArrayList<Integer>(Arrays.asList(
    9000550,//1.00  江铃汽车  汽车整车
    9000572,//2.00  海马汽车  汽车整车
    9000625,//3.00  长安汽车  汽车整车
    9000800,//4.00  一汽轿车  汽车整车
    9000868,//5.00  安凯客车  汽车整车
    9000927,//6.00  一汽夏利  汽车整车
    9000951,//7.00  中国重汽  汽车整车
    9000957,//8.00  中通客车  汽车整车
    9002537,//9.00  海立美达  汽车整车
    9002594,//10.00 比亚迪 汽车整车
    600006,//11.00  东风汽车  汽车整车
    600066,//12.00  宇通客车  汽车整车
    600104,//13.00  上汽集团  汽车整车
    600166,//14.00  福田汽车  汽车整车
    600213,//15.00  亚星客车  汽车整车
    600262,//16.00  北方股份  汽车整车
    600303,//17.00  曙光股份  汽车整车
    600375,//18.00  *ST星马 汽车整车
    600418,//19.00  江淮汽车  汽车整车
    600609,//20.00  金杯汽车  汽车整车
    600686,//21.00  金龙汽车  汽车整车
    600760,//22.00  *ST黑豹 汽车整车
    601238,//23.00  广汽集团  汽车整车
    601633//24.00  长城汽车  汽车整车
    ));
    
    
    List<Integer> listCode_73_qcpj =new ArrayList<Integer>( Arrays.asList(
        9000030,//1.00  富奥股份  汽车配件
        9000338,//2.00  潍柴动力  汽车配件
        9000559,//3.00  万向钱潮  汽车配件
        9000581,//4.00  威孚高科  汽车配件
        9000589,//5.00  黔轮胎Ａ  汽车配件
        9000599,//6.00  青岛双星  汽车配件
        9000622,//7.00  *ST恒立 汽车配件
        9000700,//8.00  模塑科技  汽车配件
        9000710,//9.00  天兴仪表  汽车配件
        9000757,//10.00 浩物股份  汽车配件
        9000760,//11.00 斯太尔 汽车配件
        9000980,//12.00 金马股份  汽车配件
        9002031,//13.00 巨轮智能  汽车配件
        9002048,//14.00 宁波华翔  汽车配件
        9002085,//15.00 万丰奥威  汽车配件
        9002126,//16.00 银轮股份  汽车配件
        9002190,//17.00 成飞集成  汽车配件
        9002213,//18.00 特 尔 佳 汽车配件
        9002239,//19.00 奥特佳 汽车配件
        9002265,//20.00 西仪股份  汽车配件
        9002283,//21.00 天润曲轴  汽车配件
        9002284,//22.00 亚太股份  汽车配件
        9002328,//23.00 新朋股份  汽车配件
        9002355,//24.00 兴民智通  汽车配件
        9002363,//25.00 隆基机械  汽车配件
        9002406,//26.00 远东传动  汽车配件
        9002434,//27.00 万里扬 汽车配件
        9002448,//28.00 中原内配  汽车配件
        9002454,//29.00 松芝股份  汽车配件
        9002488,//30.00 金固股份  汽车配件
        9002510,//31.00 天汽模 汽车配件
        9002536,//32.00 西泵股份  汽车配件
        9002553,//33.00 南方轴承  汽车配件
        9002590,//34.00 万安科技  汽车配件
        9002592,//35.00 八菱科技  汽车配件
        9002593,//36.00 日上集团  汽车配件
        9002602,//37.00 世纪华通  汽车配件
        9002625,//38.00 龙生股份  汽车配件
        9002662,//39.00 京威股份  汽车配件
        9002664,//40.00 信质电机  汽车配件
        9002703,//41.00 浙江世宝  汽车配件
        9002708,//42.00 光洋股份  汽车配件
        9002715,//43.00 登云股份  汽车配件
        9002725,//44.00 跃岭股份  汽车配件
        9002765,//45.00 蓝黛传动  汽车配件
        9002766,//46.00 索菱股份  汽车配件
        9002813,//47.00 路畅科技  汽车配件
        300176,//48.00  鸿特精密  汽车配件
        300258,//49.00  精锻科技  汽车配件
        300304,//50.00  云意电气  汽车配件
        300375,//51.00  鹏翎股份  汽车配件
        300432,//52.00  富临精工  汽车配件
        300473,//53.00  德尔股份  汽车配件
        300507,//54.00  苏奥传感  汽车配件
        300580,//55.00  贝斯特 汽车配件
        300585,//56.00  奥联电子  汽车配件
        600081,//57.00  东风科技  汽车配件
        600093,//58.00  禾嘉股份  汽车配件
        600148,//59.00  长春一东  汽车配件
        600178,//60.00  东安动力  汽车配件
        600182,//61.00  S佳通 汽车配件
        600335,//62.00  国机汽车  汽车配件
        600469,//63.00  风神股份  汽车配件
        600480,//64.00  凌云股份  汽车配件
        600501,//65.00  航天晨光  汽车配件
        600523,//66.00  贵航股份  汽车配件
        600623,//67.00  华谊集团  汽车配件
        600660,//68.00  福耀玻璃  汽车配件
        600698,//69.00  湖南天雁  汽车配件
        600699,//70.00  均胜电子  汽车配件
        600741,//71.00  华域汽车  汽车配件
        600742,//72.00  一汽富维  汽车配件
        600960,//73.00  渤海活塞  汽车配件
        601058,//74.00  赛轮金宇  汽车配件
        601127,//75.00  小康股份  汽车配件
        601163,//76.00  三角轮胎  汽车配件
        601500,//77.00  通用股份  汽车配件
        601689,//78.00  拓普集团  汽车配件
        601799,//79.00  星宇股份  汽车配件
        601966,//80.00  玲珑轮胎  汽车配件
        603006,//81.00  联明股份  汽车配件
        603009,//82.00  北特科技  汽车配件
        603023,//83.00  威帝股份  汽车配件
        603158,//84.00  腾龙股份  汽车配件
        603166,//85.00  福达股份  汽车配件
        603239,//86.00  N仙通 汽车配件
        603306,//87.00  华懋科技  汽车配件
        603319,//88.00  湘油泵 汽车配件
        603701,//89.00  德宏股份  汽车配件
        603788,//90.00  宁波高发  汽车配件
        603997,//91.00  继峰股份  汽车配件
        603035,//92.00  常熟汽饰  汽车配件
        603037//93.00  凯众股份  汽车配件
    
    ));
    
    List<Integer> listCode_74_qcfw =new ArrayList<Integer>( Arrays.asList(
    9000025,//1.00  特 力Ａ  汽车服务
    9000753,//2.00  漳州发展  汽车服务
    9002607,//3.00  亚夏汽车  汽车服务
    300100,//4.00 双林股份  汽车服务
    600297,//5.00 广汇汽车  汽车服务
    600653,//6.00 申华控股  汽车服务
    601258,//7.00 庞大集团  汽车服务
    601965,//8.00 中国汽研  汽车服务
    603377//9.00 东方时尚  汽车服务
    
    ));
    
    List<Integer> listCode_75_mtc = new ArrayList<Integer>(Arrays.asList(
          9000913,//1.00  *ST钱江 摩托车
          9001696,//2.00  宗申动力  摩托车
          600099,//3.00 林海股份  摩托车
          600877,//4.00 中国嘉陵  摩托车
          601777,//5.00 力帆股份  摩托车
          603766//6.00 隆鑫通用  摩托车
    
    ));
    
    List<Integer> listCode_76_hxzy = new ArrayList<Integer>(Arrays.asList(
                  9000153,//1.00  丰原药业  化学制药
                  9000566,//2.00  海南海药  化学制药
                  9000597,//3.00  东北制药  化学制药
                  9000606,//4.00  *ST易桥 化学制药
                  9000739,//5.00  普洛药业  化学制药
                  9000756,//6.00  新华制药  化学制药
                  9000788,//7.00  北大医药  化学制药
                  9000813,//8.00  德展健康  化学制药
                  9000908,//9.00  景峰医药  化学制药
                  9000915,//10.00 山大华特  化学制药
                  9000919,//11.00 金陵药业  化学制药
                  9000952,//12.00 广济药业  化学制药
                  9000963,//13.00 华东医药  化学制药
                  9002001,//14.00 新 和 成 化学制药
                  9002004,//15.00 华邦健康  化学制药
                  9002019,//16.00 亿帆医药  化学制药
                  9002020,//17.00 京新药业  化学制药
                  9002099,//18.00 海翔药业  化学制药
                  9002102,//19.00 冠福股份  化学制药
                  9002262,//20.00 恩华药业  化学制药
                  9002294,//21.00 信立泰 化学制药
                  9002365,//22.00 永安药业  化学制药
                  9002370,//23.00 亚太药业  化学制药
                  9002393,//24.00 力生制药  化学制药
                  9002399,//25.00 海普瑞 化学制药
                  9002411,//26.00 必康股份  化学制药
                  9002422,//27.00 科伦药业  化学制药
                  9002437,//28.00 誉衡药业  化学制药
                  9002653,//29.00 海思科 化学制药
                  9002675,//30.00 东诚药业  化学制药
                  9002688,//31.00 金河生物  化学制药
                  9002693,//32.00 双成药业  化学制药
                  9002817,//33.00 黄山胶囊  化学制药
                  9002826,//34.00 易明医药  化学制药
                  300006,//35.00  莱美药业  化学制药
                  300086,//36.00  康芝药业  化学制药
                  300110,//37.00  华仁药业  化学制药
                  300194,//38.00  福安药业  化学制药
                  300199,//39.00  翰宇药业  化学制药
                  300233,//40.00  金城医药  化学制药
                  300254,//41.00  仟源医药  化学制药
                  300267,//42.00  尔康制药  化学制药
                  300363,//43.00  博腾股份  化学制药
                  300436,//44.00  广生堂 化学制药
                  300452,//45.00  山河药辅  化学制药
                  300497,//46.00  富祥股份  化学制药
                  300558,//47.00  贝达药业  化学制药
                  300573,//48.00  兴齐眼药  化学制药
                  300584,//49.00  海辰药业  化学制药
                  600062,//50.00  华润双鹤  化学制药
                  600079,//51.00  人福医药  化学制药
                  600196,//52.00  复星医药  化学制药
                  600216,//53.00  浙江医药  化学制药
                  600267,//54.00  海正药业  化学制药
                  600276,//55.00  恒瑞医药  化学制药
                  600299,//56.00  安迪苏 化学制药
                  600380,//57.00  健康元 化学制药
                  600420,//58.00  现代制药  化学制药
                  600488,//59.00  天药股份  化学制药
                  600513,//60.00  联环药业  化学制药
                  600521,//61.00  华海药业  化学制药
                  600664,//62.00  哈药股份  化学制药
                  600789,//63.00  鲁抗医药  化学制药
                  600812,//64.00  华北制药  化学制药
                  603168,//65.00  莎普爱思  化学制药
                  603222,//66.00  济民制药  化学制药
                  603456,//67.00  九洲药业  化学制药
                  603520,//68.00  司太立 化学制药
                  603669//69.00  灵康药业  化学制药
    
    ));
    
    List<Integer> listCode_77_swzy = new ArrayList<Integer>(Arrays.asList(
    9000004,//1.00  国农科技  生物制药
    9000078,//2.00  海王生物  生物制药
    9000403,//3.00  ST生化  生物制药
    9000518,//4.00  四环生物  生物制药
    9000661,//5.00  长春高新  生物制药
    9000806,//6.00  银河生物  生物制药
    9002007,//7.00  华兰生物  生物制药
    9002030,//8.00  达安基因  生物制药
    9002038,//9.00  双鹭药业  生物制药
    9002252,//10.00 上海莱士  生物制药
    9002332,//11.00 仙琚制药  生物制药
    9002550,//12.00 千红制药  生物制药
    9002581,//13.00 未名医药  生物制药
    9002680,//14.00 长生生物  生物制药
    9002821,//15.00 凯莱英 生物制药
    300009,//16.00  安科生物  生物制药
    300119,//17.00  瑞普生物  生物制药
    300122,//18.00  智飞生物  生物制药
    300142,//19.00  沃森生物  生物制药
    300204,//20.00  舒泰神 生物制药
    300239,//21.00  东宝生物  生物制药
    300255,//22.00  常山药业  生物制药
    300289,//23.00  利德曼 生物制药
    300294,//24.00  博雅生物  生物制药
    300357,//25.00  我武生物  生物制药
    300406,//26.00  九强生物  生物制药
    300482,//27.00  万孚生物  生物制药
    300485,//28.00  赛升药业  生物制药
    300583,//29.00  赛托生物  生物制药
    600161,//30.00  天坛生物  生物制药
    600201,//31.00  生物股份  生物制药
    600645,//32.00  中源协和  生物制药
    600867,//33.00  通化东宝  生物制药
    603566,//34.00  普莱柯 生物制药
    603718//35.00  海利生物  生物制药
    
    ));
    
    
    List<Integer> listCode_78_zcy = new ArrayList<Integer>(Arrays.asList(
    9000423,//1.00  东阿阿胶  中成药
    9000513,//2.00  丽珠集团  中成药
    9000538,//3.00  云南白药  中成药
    9000590,//4.00  启迪古汉  中成药
    9000623,//5.00  吉林敖东  中成药
    9000650,//6.00  仁和药业  中成药
    9000766,//7.00  通化金马  中成药
    9000790,//8.00  泰合健康  中成药
    9000989,//9.00  九 芝 堂 中成药
    9000999,//10.00 华润三九  中成药
    9002107,//11.00 沃华医药  中成药
    9002118,//12.00 紫鑫药业  中成药
    9002166,//13.00 莱茵生物  中成药
    9002198,//14.00 嘉应制药  中成药
    9002219,//15.00 恒康医疗  中成药
    9002275,//16.00 桂林三金  中成药
    9002287,//17.00 奇正藏药  中成药
    9002317,//18.00 众生药业  中成药
    9002349,//19.00 精华制药  中成药
    9002390,//20.00 信邦制药  中成药
    9002412,//21.00 汉森制药  中成药
    9002424,//22.00 贵州百灵  中成药
    9002433,//23.00 太安堂 中成药
    9002566,//24.00 益盛药业  中成药
    9002603,//25.00 以岭药业  中成药
    9002644,//26.00 佛慈制药  中成药
    9002728,//27.00 特一药业  中成药
    9002737,//28.00 葵花药业  中成药
    9002750,//29.00 龙津药业  中成药
    9002773,//30.00 康弘药业  中成药
    300016,//31.00  北陆药业  中成药
    300026,//32.00  红日药业  中成药
    300039,//33.00  上海凯宝  中成药
    300049,//34.00  福瑞股份  中成药
    300108,//35.00  双龙股份  中成药
    300147,//36.00  香雪制药  中成药
    300158,//37.00  振东制药  中成药
    300181,//38.00  佐力药业  中成药
    300519,//39.00  新光药业  中成药
    300534,//40.00  陇神戎发  中成药
    600080,//41.00  金花股份  中成药
    600085,//42.00  同仁堂 中成药
    600129,//43.00  太极集团  中成药
    600211,//44.00  西藏药业  中成药
    600222,//45.00  太龙药业  中成药
    600252,//46.00  中恒集团  中成药
    600285,//47.00  羚锐制药  中成药
    600329,//48.00  中新药业  中成药
    600332,//49.00  白云山 中成药
    600351,//50.00  亚宝药业  中成药
    600422,//51.00  昆药集团  中成药
    600436,//52.00  片仔癀 中成药
    600479,//53.00  千金药业  中成药
    600518,//54.00  康美药业  中成药
    600535,//55.00  天士力 中成药
    600557,//56.00  康缘药业  中成药
    600566,//57.00  济川药业  中成药
    600572,//58.00  康恩贝 中成药
    600594,//59.00  益佰制药  中成药
    600613,//60.00  神奇制药  中成药
    600671,//61.00  天目药业  中成药
    600750,//62.00  江中药业  中成药
    600771,//63.00  广誉远 中成药
    600781,//64.00  辅仁药业  中成药
    600976,//65.00  健民集团  中成药
    600993,//66.00  马应龙 中成药
    603567,//67.00  珍宝岛 中成药
    603858,//68.00  步长制药  中成药
    603998//69.00  方盛制药  中成药
    ));
    
    List<Integer> listCode_79_bh = new ArrayList<Integer>(Arrays.asList(

          9000417,//1.00  合肥百货  百货
          9000419,//2.00  通程控股  百货
          9000501,//3.00  鄂武商Ａ  百货
          9000516,//4.00  国际医学  百货
          9000560,//5.00  昆百大Ａ  百货
          9000564,//6.00  西安民生  百货
          9000679,//7.00  大连友谊  百货
          9000715,//8.00  中兴商业  百货
          9000785,//9.00  武汉中商  百货
          9000882,//10.00 华联股份  百货
          9002187,//11.00 广百股份  百货
          9002277,//12.00 友阿股份  百货
          9002419,//13.00 天虹商场  百货
          9002561,//14.00 徐家汇 百货
          300413,//15.00  快乐购 百货
          600280,//16.00  中央商场  百货
          600306,//17.00  *ST商城 百货
          600327,//18.00  大东方 百货
          600515,//19.00  海航基础  百货
          600628,//20.00  新世界 百货
          600655,//21.00  豫园商城  百货
          600682,//22.00  南京新百  百货
          600693,//23.00  东百集团  百货
          600694,//24.00  大商股份  百货
          600697,//25.00  欧亚集团  百货
          600712,//26.00  南宁百货  百货
          600723,//27.00  首商股份  百货
          600729,//28.00  重庆百货  百货
          600738,//29.00  兰州民百  百货
          600774,//30.00  汉商集团  百货
          600778,//31.00  友好集团  百货
          600785,//32.00  新华百货  百货
          600814,//33.00  杭州解百  百货
          600821,//34.00  津劝业 百货
          600824,//35.00  益民集团  百货
          600828,//36.00  茂业商业  百货
          600838,//37.00  上海九百  百货
          600857,//38.00  宁波中百  百货
          600858,//39.00  银座股份  百货
          600859,//40.00  王府井 百货
          600861,//41.00  北京城乡  百货
          600865,//42.00  百大集团  百货
          600891,//43.00  秋林集团  百货
          603031,//44.00  安德利 百货
          603101,//45.00  汇嘉时代  百货
          603123//46.00  翠微股份  百货
    ));
    
    
    List<Integer> listCode_80_csls = new ArrayList<Integer>(Arrays.asList(
    9000759,//1.00  中百集团  超市连锁
    9002251,//2.00  步 步 高 超市连锁
    9002264,//3.00  新 华 都 超市连锁
    9002336,//4.00  *ST人乐 超市连锁
    9002697,//5.00  红旗连锁  超市连锁
    600361,//6.00 华联综超  超市连锁
    600827,//7.00 百联股份  超市连锁
    601010,//8.00 文峰股份  超市连锁
    601116,//9.00 三江购物  超市连锁
    601933,//10.00  永辉超市  超市连锁
    603708//11.00  家家悦 超市连锁
    
    ));
    
    List<Integer> listCode_81_dqls = new ArrayList<Integer>(Arrays.asList(
        9002024,//1.00  苏宁云商  电器连锁
        9002416,//2.00  爱施德 电器连锁
        600898//3.00 三联商社  电器连锁
    ));
    List<Integer> listCode_82_yysy = new ArrayList<Integer>(Arrays.asList(

      9000028,//1.00  国药一致  医药商业
      9000411,//2.00  英特集团  医药商业
      9000705,//3.00  浙江震元  医药商业
      9002462,//4.00  嘉事堂 医药商业
      9002589,//5.00  瑞康医药  医药商业
      9002727,//6.00  一心堂 医药商业
      9002758,//7.00  华通医药  医药商业
      9002788,//8.00  鹭燕医药  医药商业
      600056,//9.00 中国医药  医药商业
      600090,//10.00  同济堂 医药商业
      600511,//11.00  国药股份  医药商业
      600713,//12.00  南京医药  医药商业
      600829,//13.00  人民同泰  医药商业
      600833,//14.00  第一医药  医药商业
      600998,//15.00  九州通 医药商业
      601607,//16.00  上海医药  医药商业
      603108,//17.00  润达医疗  医药商业
      603368,//18.00  柳州医药  医药商业
      603716,//19.00  塞力斯 医药商业
      603883,//20.00  老百姓 医药商业
      603939//21.00  益丰药房  医药商业
    ));
    
    List<Integer> listCode_83_qtsy = new ArrayList<Integer>(Arrays.asList(
        9000026,//1.00  飞亚达Ａ  其他商业
        9000829,//2.00  天音控股  其他商业
        9002556,//3.00  辉隆股份  其他商业
        9002780,//4.00  三夫户外  其他商业
        300022,//5.00 吉峰农机  其他商业
        600122,//6.00 宏图高科  其他商业
        603777//7.00 来伊份 其他商业
    
    ));
    
    List<Integer> listCode_84_spc = new ArrayList<Integer>(Arrays.asList(
    9002344,//1.00  海宁皮城  商品城
    600415,//2.00 小商品城  商品城
    600790//3.00 轻纺城 商品城
    ));
    
    List<Integer> listCode_85_pfy = new ArrayList<Integer>(Arrays.asList(
          9000587,//1.00  金洲慈航  批发业
          9000638,//2.00  万方发展  批发业
          9000652,//3.00  泰达股份  批发业
          9000906,//4.00  物产中拓  批发业
          9002441,//5.00  众业达 批发业
          300538,//6.00 同益股份  批发业
          600753//7.00 东方银星  批发业
    
    ));
    
    List<Integer> listCode_86_cpy = new ArrayList<Integer>(Arrays.asList(
          9000504,//1.00  *ST生物 出版业
          9000719,//2.00  大地传媒  出版业
          9000793,//3.00  华闻传媒  出版业
          9002181,//4.00  粤 传 媒 出版业
          300148,//5.00 天舟文化  出版业
          300364,//6.00 中文在线  出版业
          600229,//7.00 城市传媒  出版业
          600373,//8.00 中文传媒  出版业
          600551,//9.00 时代出版  出版业
          600633,//10.00  浙报传媒  出版业
          600757,//11.00  长江传媒  出版业
          600825,//12.00  新华传媒  出版业
          601098,//13.00  中南传媒  出版业
          601801,//14.00  皖新传媒  出版业
          601811,//15.00  新华文轩  出版业
          601900,//16.00  南方传媒  出版业
          601928,//17.00  凤凰传媒  出版业
          601999,//18.00  出版传媒  出版业
          603999,//19.00  读者传媒  出版业
          601858//20.00  中国科传  出版业
    
    ));
    
    List<Integer> listCode_87_ysyx = new ArrayList<Integer>(Arrays.asList(
    9000156,//1.00  华数传媒  影视音像
    9000665,//2.00  湖北广电  影视音像
    9000673,//3.00  当代东方  影视音像
    9000835,//4.00  长城动漫  影视音像
    9000917,//5.00  电广传媒  影视音像
    9002071,//6.00  长城影视  影视音像
    9002143,//7.00  印纪传媒  影视音像
    9002238,//8.00  天威视讯  影视音像
    9002292,//9.00  奥飞娱乐  影视音像
    9002343,//10.00 慈文传媒  影视音像
    9002445,//11.00 中南文化  影视音像
    9002502,//12.00 骅威文化  影视音像
    9002624,//13.00 完美世界  影视音像
    9002739,//14.00 万达院线  影视音像
    300027,//15.00  华谊兄弟  影视音像
    300133,//16.00  华策影视  影视音像
    300251,//17.00  光线传媒  影视音像
    300291,//18.00  华录百纳  影视音像
    300336,//19.00  新文化 影视音像
    300426,//20.00  唐德影视  影视音像
    300528,//21.00  幸福蓝海  影视音像
    600037,//22.00  歌华有线  影视音像
    600088,//23.00  中视传媒  影视音像
    600136,//24.00  当代明诚  影视音像
    600576,//25.00  万家文化  影视音像
    600637,//26.00  东方明珠  影视音像
    600715,//27.00  文投控股  影视音像
    600831,//28.00  广电网络  影视音像
    600936,//29.00  广西广电  影视音像
    600959,//30.00  江苏有线  影视音像
    600977,//31.00  中国电影  影视音像
    600996,//32.00  贵广网络  影视音像
    601595,//33.00  上海电影  影视音像
    601599,//34.00  鹿港文化  影视音像
    601929,//35.00  吉视传媒  影视音像
    603598//36.00  引力传媒  影视音像
    
    ));
    
    List<Integer> listCode_88_lyfw = new ArrayList<Integer>(Arrays.asList(
        9000610,//1.00  西安旅游  旅游服务
        9000613,//2.00  大东海A  旅游服务
        9000796,//3.00  凯撒旅游  旅游服务
        9000802,//4.00  北京文化  旅游服务
        9002558,//5.00  世纪游轮  旅游服务
        9002707,//6.00  众信旅游  旅游服务
        300178,//7.00 腾邦国际  旅游服务
        600138,//8.00 中青旅 旅游服务
        600358,//9.00 国旅联合  旅游服务
        600706,//10.00  曲江文旅  旅游服务
        601888,//11.00  中国国旅  旅游服务
        603099,//12.00  长白山 旅游服务
        603199,//13.00  九华旅游  旅游服务
        603869//14.00  北部湾旅  旅游服务
    ));
    
    List<Integer> listCode_89_lyjd = new ArrayList<Integer>(Arrays.asList(
        9000069,//1.00  华侨城Ａ  旅游景点
        9000430,//2.00  张家界 旅游景点
        9000888,//3.00  峨眉山Ａ  旅游景点
        9000978,//4.00  桂林旅游  旅游景点
        9002033,//5.00  丽江旅游  旅游景点
        9002059,//6.00  云南旅游  旅游景点
        9002159,//7.00  三特索道  旅游景点
        300144,//8.00 宋城演艺  旅游景点
        600054,//9.00 黄山旅游  旅游景点
        600555,//10.00  海航创新  旅游景点
        600593,//11.00  大连圣亚  旅游景点
        600749//12.00  西藏旅游  旅游景点
    ));
    
    List<Integer> listCode_90_jczz = new ArrayList<Integer>(Arrays.asList(
    
          9000410,//1.00  沈阳机床  机床制造
          9000837,//2.00  秦川机床  机床制造
          9002248,//3.00  华东数控  机床制造
          9002520,//4.00  日发精机  机床制造
          9002559,//5.00  亚威股份  机床制造
          300161,//6.00 华中数控  机床制造
          300441,//7.00 鲍斯股份  机床制造
          600243,//8.00 青海华鼎  机床制造
          600806,//9.00 *ST昆机 机床制造
          601882,//10.00  海天精工  机床制造
          603011//11.00  合锻智能  机床制造
    
    ));
    List<Integer> listCode_91_jxjj = new ArrayList<Integer>(Arrays.asList(
          9000530,//1.00  大冷股份  机械基件
          9000570,//2.00  苏常柴Ａ  机械基件
          9000595,//3.00  宝塔实业  机械基件
          9000617,//4.00  *ST济柴 机械基件
          9000678,//5.00  襄阳轴承  机械基件
          9000777,//6.00  中核科技  机械基件
          9000816,//7.00  智慧农业  机械基件
          9000856,//8.00  *ST冀装 机械基件
          9000880,//9.00  潍柴重机  机械基件
          9000903,//10.00 云内动力  机械基件
          9002026,//11.00 山东威达  机械基件
          9002046,//12.00 轴研科技  机械基件
          9002050,//13.00 三花智控  机械基件
          9002101,//14.00 广东鸿图  机械基件
          9002122,//15.00 天马股份  机械基件
          9002147,//16.00 新光圆成  机械基件
          9002150,//17.00 通润装备  机械基件
          9002272,//18.00 川润股份  机械基件
          9002342,//19.00 巨力索具  机械基件
          9002347,//20.00 泰尔股份  机械基件
          9002418,//21.00 康盛股份  机械基件
          9002435,//22.00 长江润发  机械基件
          9002438,//23.00 江苏神通  机械基件
          9002472,//24.00 双环传动  机械基件
          9002480,//25.00 新筑股份  机械基件
          9002514,//26.00 宝馨科技  机械基件
          9002552,//27.00 宝鼎科技  机械基件
          9002598,//28.00 山东章鼓  机械基件
          9002633,//29.00 申科股份  机械基件
          9002747,//30.00 埃斯顿 机械基件
          9002760,//31.00 凤形股份  机械基件
          9002795,//32.00 永和智控  机械基件
          9002823,//33.00 凯中精密  机械基件
          300091,//34.00  金通灵 机械基件
          300095,//35.00  华伍股份  机械基件
          300151,//36.00  昌红科技  机械基件
          300257,//37.00  开山股份  机械基件
          300260,//38.00  新莱应材  机械基件
          300266,//39.00  兴源环境  机械基件
          300391,//40.00  康跃科技  机械基件
          300420,//41.00  五洋科技  机械基件
          300421,//42.00  力星股份  机械基件
          300435,//43.00  中泰股份  机械基件
          300464,//44.00  星徽精密  机械基件
          300470,//45.00  日机密封  机械基件
          300488,//46.00  恒锋工具  机械基件
          300503,//47.00  昊志机电  机械基件
          600114,//48.00  东睦股份  机械基件
          600421,//49.00  仰帆控股  机械基件
          600520,//50.00  *ST中发 机械基件
          600592,//51.00  龙溪股份  机械基件
          600619,//52.00  海立股份  机械基件
          600765,//53.00  中航重机  机械基件
          600841,//54.00  上柴股份  机械基件
          601002,//55.00  晋亿实业  机械基件
          601177,//56.00  杭齿前进  机械基件
          601218,//57.00  吉鑫科技  机械基件
          601369,//58.00  陕鼓动力  机械基件
          603315,//59.00  福鞍股份  机械基件
          603667,//60.00  五洲新春  机械基件
          603726//61.00  朗迪集团  机械基件
    
    ));
    
    List<Integer> listCode_92_hgjx = new ArrayList<Integer>(Arrays.asList(
      9000852,//1.00  石化机械  化工机械
      9002278,//2.00  神开股份  化工机械
      9002337,//3.00  赛象科技  化工机械
      9002353,//4.00  杰瑞股份  化工机械
      9002430,//5.00  杭氧股份  化工机械
      9002490,//6.00  山东墨龙  化工机械
      9002564,//7.00  天沃科技  化工机械
      9002698,//8.00  博实股份  化工机械
      300228,//9.00 富瑞特装  化工机械
      600579,//10.00  天华院 化工机械
      601798//11.00  蓝科高新  化工机械
    ));
    
    List<Integer> listCode_93_qgjx = new ArrayList<Integer>(Arrays.asList(
      9000039,//1.00  中集集团  轻工机械
      9000821,//2.00  京山轻机  轻工机械
      9002209,//3.00  达 意 隆 轻工机械
      9002282,//4.00  博深工具  轻工机械
      9002444,//5.00  巨星科技  轻工机械
      9002611,//6.00  东方精工  轻工机械
      300126,//7.00 锐奇股份  轻工机械
      300173,//8.00 智慧松德  轻工机械
      300195,//9.00 长荣股份  轻工机械
      300442//10.00  普丽盛 轻工机械
    
    ));
    List<Integer> listCode_94_fzjx = new ArrayList<Integer>(Arrays.asList(
    9000666,//1.00  经纬纺机  纺织机械
    9002021,//2.00  中捷资源  纺织机械
    9002196,//3.00  方正电机  纺织机械
    9002722,//4.00  金轮股份  纺织机械
    300307,//5.00 慈星股份  纺织机械
    300384,//6.00 三联虹普  纺织机械
    600302,//7.00 标准股份  纺织机械
    600843,//8.00 上工申贝  纺织机械
    603337//9.00 杰克股份  纺织机械
    ));
    
    List<Integer> listCode_95_lyjx = new ArrayList<Integer>(Arrays.asList(
    9002532,//1.00  新界泵业  农用机械
    9002779,//2.00  中坚科技  农用机械
    300159,//3.00 新研股份  农用机械
    600218,//4.00 全柴动力  农用机械
    601038,//5.00 一拖股份  农用机械
    603789//6.00 星光农机  农用机械
    ));
    List<Integer> listCode_96_zyjx = new ArrayList<Integer>(Arrays.asList(
    9000404,//1.00  华意压缩  专用机械
    9000551,//2.00  创元科技  专用机械
    9000925,//3.00  众合科技  专用机械
    9002006,//4.00  精功科技  专用机械
    9002192,//5.00  融捷股份  专用机械
    9002204,//6.00  大连重工  专用机械
    9002255,//7.00  海陆重工  专用机械
    9002366,//8.00  台海核电  专用机械
    9002509,//9.00  天广中茂  专用机械
    9002529,//10.00 海源机械  专用机械
    9002530,//11.00 丰东股份  专用机械
    9002534,//12.00 杭锅股份  专用机械
    9002595,//13.00 豪迈科技  专用机械
    9002613,//14.00 北玻股份  专用机械
    9002621,//15.00 三垒股份  专用机械
    9002630,//16.00 华西能源  专用机械
    9002639,//17.00 雪人股份  专用机械
    9002645,//18.00 华宏科技  专用机械
    9002651,//19.00 利君股份  专用机械
    9002686,//20.00 亿利达 专用机械
    9002690,//21.00 美亚光电  专用机械
    9002691,//22.00 冀凯股份  专用机械
    9002786,//23.00 银宝山新  专用机械
    9002793,//24.00 东音股份  专用机械
    9002796,//25.00 世嘉科技  专用机械
    9002816,//26.00 和科达 专用机械
    9002833,//27.00 弘亚数控  专用机械
    9002837,//28.00 英维克 专用机械
    300023,//29.00  宝德股份  专用机械
    300024,//30.00  机器人 专用机械
    300029,//31.00  天龙光电  专用机械
    300092,//32.00  科新机电  专用机械
    300116,//33.00  坚瑞沃能  专用机械
    300145,//34.00  中金环境  专用机械
    300193,//35.00  佳士科技  专用机械
    300201,//36.00  海伦哲 专用机械
    300202,//37.00  聚龙股份  专用机械
    300210,//38.00  森远股份  专用机械
    300249,//39.00  依米康 专用机械
    300263,//40.00  隆华节能  专用机械
    300276,//41.00  三丰智能  专用机械
    300278,//42.00  华昌达 专用机械
    300280,//43.00  南通锻压  专用机械
    300281,//44.00  金明精机  专用机械
    300293,//45.00  蓝英装备  专用机械
    300309,//46.00  吉艾科技  专用机械
    300316,//47.00  晶盛机电  专用机械
    300334,//48.00  津膜科技  专用机械
    300368,//49.00  汇金股份  专用机械
    300382,//50.00  斯莱克 专用机械
    300400,//51.00  劲拓股份  专用机械
    300402,//52.00  宝色股份  专用机械
    300411,//53.00  金盾股份  专用机械
    300415,//54.00  伊之密 专用机械
    300434,//55.00  金石东方  专用机械
    300443,//56.00  金雷风电  专用机械
    300450,//57.00  先导智能  专用机械
    300457,//58.00  赢合科技  专用机械
    300461,//59.00  田中精机  专用机械
    300462,//60.00  华铭智能  专用机械
    300471,//61.00  厚普股份  专用机械
    300472,//62.00  新元科技  专用机械
    300475,//63.00  聚隆科技  专用机械
    300483,//64.00  沃施股份  专用机械
    300486,//65.00  东杰智能  专用机械
    300499,//66.00  高澜股份  专用机械
    300509,//67.00  新美星 专用机械
    300512,//68.00  中亚股份  专用机械
    300521,//69.00  爱司凯 专用机械
    300526,//70.00  中潜股份  专用机械
    300527,//71.00  华舟应急  专用机械
    300540,//72.00  深冷股份  专用机械
    300545,//73.00  联得装备  专用机械
    300549,//74.00  优德精密  专用机械
    300551,//75.00  古鳌科技  专用机械
    300569,//76.00  天能重工  专用机械
    600184,//77.00  光电股份  专用机械
    600435,//78.00  北方导航  专用机械
    600475,//79.00  华光股份  专用机械
    600499,//80.00  科达洁能  专用机械
    600855,//81.00  航天长峰  专用机械
    600860,//82.00  京城股份  专用机械
    601226,//83.00  华电重工  专用机械
    601608,//84.00  中信重工  专用机械
    603012,//85.00  创力集团  专用机械
    603029,//86.00  天鹅股份  专用机械
    603036,//87.00  如通股份  专用机械
    603066,//88.00  音飞储存  专用机械
    603085,//89.00  天成自控  专用机械
    603088,//90.00  宁波精达  专用机械
    603090,//91.00  宏盛股份  专用机械
    603131,//92.00  上海沪工  专用机械
    603159,//93.00  上海亚虹  专用机械
    603169,//94.00  兰石重装  专用机械
    603203,//95.00  快克股份  专用机械
    603298,//96.00  杭叉集团  专用机械
    603308,//97.00  应流股份  专用机械
    603311,//98.00  金海环境  专用机械
    603318,//99.00  派思股份  专用机械
    603338,//100.00 浙江鼎力  专用机械
    603339,//101.00 四方冷链  专用机械
    603686,//102.00 龙马环卫  专用机械
    603698,//103.00 航天工程  专用机械
    603699,//104.00 纽威股份  专用机械
    603800,//105.00 道森股份  专用机械
    603901,//106.00 永创智能  专用机械
    603690//107.00 至纯科技  专用机械
    ));
    
    List<Integer> listCode_97_tl = new ArrayList<Integer>(Arrays.asList(
    600125,//1.00 铁龙物流  铁路
    601006,//2.00 大秦铁路  铁路
    601333//3.00 广深铁路  铁路
    ));
    List<Integer> listCode_98_sy = new ArrayList<Integer>(Arrays.asList(
    9000520,//1.00  长航凤凰  水运
    9002320,//2.00  海峡股份  水运
    600026,//3.00 中远海能  水运
    600242,//4.00 中昌数据  水运
    600428,//5.00 中远海特  水运
    600575,//6.00 皖江物流  水运
    600692,//7.00 亚通股份  水运
    600751,//8.00 天海投资  水运
    600798,//9.00 宁波海运  水运
    600896,//10.00  览海投资  水运
    601866,//11.00  中远海发  水运
    601872,//12.00  招商轮船  水运
    601919,//13.00  中远海控  水运
    603167//14.00  渤海轮渡  水运
    ));
    List<Integer> listCode_99_ky = new ArrayList<Integer>(Arrays.asList(
    9000099,//1.00  中信海直  空运
    600029,//2.00 南方航空  空运
    600115,//3.00 东方航空  空运
    600221,//4.00 海南航空  空运
    601021,//5.00 春秋航空  空运
    601111,//6.00 中国国航  空运
    603885//7.00 吉祥航空  空运
    ));
    
    List<Integer> listCode_100_gl = new ArrayList<Integer>(Arrays.asList(
    9000996,//1.00  中国中期  公路
    9002357,//2.00  富临运业  公路
    9002627,//3.00  宜昌交运  公路
    9002682,//4.00  龙洲股份  公路
    600561,//5.00 江西长运  公路
    603069,//6.00 海汽集团  公路
    603223,//7.00 恒通股份  公路
    603032//8.00 德新交运  公路
    ));
    
    List<Integer> listCode_101_lq = new ArrayList<Integer>(Arrays.asList(
    9000429,//1.00  粤高速Ａ  路桥
    9000548,//2.00  湖南投资  路桥
    9000828,//3.00  东莞控股  路桥
    9000886,//4.00  海南高速  路桥
    9000900,//5.00  现代投资  路桥
    9000916,//6.00  华北高速  路桥
    600012,//7.00 皖通高速  路桥
    600020,//8.00 中原高速  路桥
    600033,//9.00 福建高速  路桥
    600035,//10.00  楚天高速  路桥
    600106,//11.00  重庆路桥  路桥
    600269,//12.00  赣粤高速  路桥
    600350,//13.00  山东高速  路桥
    600368,//14.00  五洲交通  路桥
    600377,//15.00  宁沪高速  路桥
    600548,//16.00  深高速 路桥
    601107,//17.00  四川成渝  路桥
    601188,//18.00  龙江交通  路桥
    601518//19.00  吉林高速  路桥
    ));
    
    List<Integer> listCode_102_jc = new ArrayList<Integer>(Arrays.asList(
    9000089,//1.00  深圳机场  机场
    600004,//2.00 白云机场  机场
    600009,//3.00 上海机场  机场
    600897//4.00 厦门空港  机场
    ));
    List<Integer> listCode_103_gk = new ArrayList<Integer>(Arrays.asList(
    9000022,//1.00  深赤湾Ａ  港口
    9000088,//2.00  盐 田 港 港口
    9000507,//3.00  珠海港 港口
    9000582,//4.00  北部湾港  港口
    9000905,//5.00  厦门港务  港口
    9002040,//6.00  南 京 港 港口
    600017,//7.00 日照港 港口
    600018,//8.00 上港集团  港口
    600190,//9.00 锦州港 港口
    600279,//10.00  重庆港九  港口
    600317,//11.00  营口港 港口
    600717,//12.00  天津港 港口
    601000,//13.00  唐山港 港口
    601008,//14.00  连云港 港口
    601018,//15.00  宁波港 港口
    601880//16.00  大连港 港口
    ));
    List<Integer> listCode_104_jzsg = new ArrayList<Integer>(Arrays.asList(
    9000010,//1.00  美丽生态  建筑施工
    9000065,//2.00  北方国际  建筑施工
    9000090,//3.00  天健集团  建筑施工
    9000498,//4.00  山东路桥  建筑施工
    9000928,//5.00  中钢国际  建筑施工
    9000961,//6.00  中南建设  建筑施工
    9002051,//7.00  中工国际  建筑施工
    9002060,//8.00  粤 水 电 建筑施工
    9002062,//9.00  宏润建设  建筑施工
    9002116,//10.00 中国海诚  建筑施工
    9002135,//11.00 东南网架  建筑施工
    9002140,//12.00 东华科技  建筑施工
    9002178,//13.00 延华智能  建筑施工
    9002307,//14.00 北新路桥  建筑施工
    9002310,//15.00 东方园林  建筑施工
    9002323,//16.00 雅百特 建筑施工
    9002374,//17.00 丽鹏股份  建筑施工
    9002431,//18.00 棕榈股份  建筑施工
    9002469,//19.00 三维工程  建筑施工
    9002542,//20.00 中化岩土  建筑施工
    9002586,//21.00 围海股份  建筑施工
    9002628,//22.00 成都路桥  建筑施工
    9002659,//23.00 中泰桥梁  建筑施工
    9002663,//24.00 普邦股份  建筑施工
    9002717,//25.00 岭南园林  建筑施工
    9002738,//26.00 中矿资源  建筑施工
    9002755,//27.00 东方新星  建筑施工
    9002775,//28.00 文科园林  建筑施工
    300237,//29.00  美晨科技  建筑施工
    300284,//30.00  苏交科 建筑施工
    300492,//31.00  山鼎设计  建筑施工
    300495,//32.00  美尚生态  建筑施工
    300500,//33.00  苏州设计  建筑施工
    300506,//34.00  名家汇 建筑施工
    300517,//35.00  海波重科  建筑施工
    300536,//36.00  农尚环境  建筑施工
    600039,//37.00  四川路桥  建筑施工
    600068,//38.00  葛洲坝 建筑施工
    600083,//39.00  博信股份  建筑施工
    600170,//40.00  上海建工  建筑施工
    600248,//41.00  延长化建  建筑施工
    600284,//42.00  浦东建设  建筑施工
    600326,//43.00  西藏天路  建筑施工
    600491,//44.00  龙元建设  建筑施工
    600502,//45.00  安徽水利  建筑施工
    600512,//46.00  腾达建设  建筑施工
    600528,//47.00  中铁二局  建筑施工
    600545,//48.00  新疆城建  建筑施工
    600610,//49.00  中毅达 建筑施工
    600629,//50.00  华建集团  建筑施工
    600769,//51.00  祥龙电业  建筑施工
    600820,//52.00  隧道股份  建筑施工
    600853,//53.00  龙建股份  建筑施工
    600970,//54.00  中材国际  建筑施工
    601117,//55.00  中国化学  建筑施工
    601186,//56.00  中国铁建  建筑施工
    601390,//57.00  中国中铁  建筑施工
    601611,//58.00  中国核建  建筑施工
    601618,//59.00  中国中冶  建筑施工
    601668,//60.00  中国建筑  建筑施工
    601669,//61.00  中国电建  建筑施工
    601789,//62.00  宁波建工  建筑施工
    601800,//63.00  中国交建  建筑施工
    603007,//64.00  花王股份  建筑施工
    603017,//65.00  中衡设计  建筑施工
    603018,//66.00  中设集团  建筑施工
    603060,//67.00  国检集团  建筑施工
    603778,//68.00  乾景园林  建筑施工
    603843,//69.00  XD正平股 建筑施工
    603887,//70.00  城地股份  建筑施工
    603909,//71.00  合诚股份  建筑施工
    603959,//72.00  百利科技  建筑施工
    603979//73.00  金诚信 建筑施工
    ));
    
    List<Integer> listCode_105_jzzs = new ArrayList<Integer>(Arrays.asList(
    9000018,//1.00  神州长城  装修装饰
    9002047,//2.00  宝鹰股份  装修装饰
    9002081,//3.00  金 螳 螂 装修装饰
    9002163,//4.00  中航三鑫  装修装饰
    9002247,//5.00  帝龙文化  装修装饰
    9002325,//6.00  洪涛股份  装修装饰
    9002375,//7.00  亚厦股份  装修装饰
    9002482,//8.00  广田集团  装修装饰
    9002504,//9.00  弘高创意  装修装饰
    9002620,//10.00 瑞和股份  装修装饰
    9002713,//11.00 东易日盛  装修装饰
    9002781,//12.00 奇信股份  装修装饰
    9002789,//13.00 建艺集团  装修装饰
    9002811,//14.00 亚泰国际  装修装饰
    9002822,//15.00 中装建设  装修装饰
    9002830,//16.00 名雕股份  装修装饰
    300117,//17.00  嘉寓股份  装修装饰
    600193,//18.00  创兴资源  装修装饰
    601886,//19.00  江河集团  装修装饰
    603030,//20.00  全筑股份  装修装饰
    603098,//21.00  森特股份  装修装饰
    603828,//22.00  柯利达 装修装饰
    603929//23.00  N亚翔 装修装饰
    ));
    List<Integer> listCode_106_qgdc = new ArrayList<Integer>(Arrays.asList(
    9000002,//1.00  万 科Ａ  全国地产
    9000014,//2.00  沙河股份  全国地产
    9000031,//3.00  中粮地产  全国地产
    9000036,//4.00  华联控股  全国地产
    9000040,//5.00  东旭蓝天  全国地产
    9000042,//6.00  中洲控股  全国地产
    9000043,//7.00  中航地产  全国地产
    9000046,//8.00  泛海控股  全国地产
    9000402,//9.00  金 融 街 全国地产
    9000616,//10.00 海航投资  全国地产
    9000620,//11.00 新华联 全国地产
    9000667,//12.00 美好置业  全国地产
    9000736,//13.00 中房地产  全国地产
    9000797,//14.00 中国武夷  全国地产
    9000918,//15.00 嘉凯城 全国地产
    9001979,//16.00 招商蛇口  全国地产
    9002133,//17.00 广宇集团  全国地产
    9002146,//18.00 荣盛发展  全国地产
    600048,//19.00  保利地产  全国地产
    600067,//20.00  冠城大通  全国地产
    600077,//21.00  宋都股份  全国地产
    600162,//22.00  香江控股  全国地产
    600173,//23.00  卧龙地产  全国地产
    600208,//24.00  新湖中宝  全国地产
    600240,//25.00  华业资本  全国地产
    600383,//26.00  金地集团  全国地产
    600393,//27.00  粤泰股份  全国地产
    600510,//28.00  黑牡丹 全国地产
    600565,//29.00  迪马股份  全国地产
    600606,//30.00  绿地控股  全国地产
    600621,//31.00  华鑫股份  全国地产
    600657,//32.00  信达地产  全国地产
    600665,//33.00  天地源 全国地产
    600684,//34.00  珠江实业  全国地产
    600708,//35.00  光明地产  全国地产
    600748,//36.00  上实发展  全国地产
    600791,//37.00  京能置业  全国地产
    600823//38.00  世茂股份  全国地产
    ));
    List<Integer> listCode_107_qydc = new ArrayList<Integer>(Arrays.asList(
    9000006,//1.00  深振业Ａ  区域地产
    9000011,//2.00  深物业A  区域地产
    9000029,//3.00  深深房Ａ  区域地产
    9000506,//4.00  中润资源  区域地产
    9000514,//5.00  渝 开 发 区域地产
    9000517,//6.00  荣安地产  区域地产
    9000534,//7.00  万泽股份  区域地产
    9000537,//8.00  广宇发展  区域地产
    9000540,//9.00  中天城投  区域地产
    9000567,//10.00 海德股份  区域地产
    9000573,//11.00 粤宏远Ａ  区域地产
    9000608,//12.00 阳光股份  区域地产
    9000609,//13.00 绵石投资  区域地产
    9000631,//14.00 顺发恒业  区域地产
    9000656,//15.00 金科股份  区域地产
    9000668,//16.00 荣丰控股  区域地产
    9000671,//17.00 阳 光 城 区域地产
    9000691,//18.00 ST亚太  区域地产
    9000718,//19.00 苏宁环球  区域地产
    9000732,//20.00 泰禾集团  区域地产
    9000809,//21.00 铁岭新城  区域地产
    9000838,//22.00 财信发展  区域地产
    9000863,//23.00 三湘印象  区域地产
    9000897,//24.00 津滨发展  区域地产
    9000926,//25.00 福星股份  区域地产
    9000931,//26.00 中 关 村 区域地产
    9000965,//27.00 天保基建  区域地产
    9000979,//28.00 中弘股份  区域地产
    9000981,//29.00 银亿股份  区域地产
    9002016,//30.00 世荣兆业  区域地产
    9002077,//31.00 大港股份  区域地产
    9002208,//32.00 合肥城建  区域地产
    9002244,//33.00 滨江集团  区域地产
    9002305,//34.00 南国置业  区域地产
    9002314,//35.00 南山控股  区域地产
    600052,//36.00  浙江广厦  区域地产
    600053,//37.00  九鼎投资  区域地产
    600094,//38.00  大名城 区域地产
    600095,//39.00  哈高科 区域地产
    600113,//40.00  浙江东日  区域地产
    600159,//41.00  大龙地产  区域地产
    600185,//42.00  格力地产  区域地产
    600223,//43.00  鲁商置业  区域地产
    600225,//44.00  天津松江  区域地产
    600239,//45.00  云南城投  区域地产
    600246,//46.00  万通地产  区域地产
    600266,//47.00  北京城建  区域地产
    600322,//48.00  天房发展  区域地产
    600325,//49.00  华发股份  区域地产
    600340,//50.00  华夏幸福  区域地产
    600376,//51.00  首开股份  区域地产
    600466,//52.00  蓝光发展  区域地产
    600503,//53.00  华丽家族  区域地产
    600533,//54.00  栖霞建设  区域地产
    600568,//55.00  中珠医疗  区域地产
    600622,//56.00  嘉宝集团  区域地产
    600638,//57.00  新黄浦 区域地产
    600641,//58.00  万业企业  区域地产
    600649,//59.00  城投控股  区域地产
    600675,//60.00  *ST中企 区域地产
    600683,//61.00  京投发展  区域地产
    600696,//62.00  匹凸匹 区域地产
    600716,//63.00  凤凰股份  区域地产
    600724,//64.00  宁波富达  区域地产
    600733,//65.00  S前锋 区域地产
    600743,//66.00  华远地产  区域地产
    600773,//67.00  西藏城投  区域地产
    600807,//68.00  天业股份  区域地产
    600890,//69.00  中房股份  区域地产
    601155,//70.00  新城控股  区域地产
    601588,//71.00  北辰实业  区域地产
    600732//72.00  *ST新梅 区域地产

    ));
    List<Integer> listCode_108_yqkf = new ArrayList<Integer>(Arrays.asList(
    9000628,//1.00  高新发展  园区开发
    600007,//2.00 中国国贸  园区开发
    600064,//3.00 南京高科  园区开发
    600082,//4.00 海泰发展  园区开发
    600133,//5.00 东湖高新  园区开发
    600215,//6.00 长春经开  园区开发
    600463,//7.00 空港股份  园区开发
    600604,//8.00 市北高新  园区开发
    600639,//9.00 浦东金桥  园区开发
    600648,//10.00  外高桥 园区开发
    600658,//11.00  电子城 园区开发
    600663,//12.00  陆家嘴 园区开发
    600736,//13.00  苏州高新  园区开发
    600848,//14.00  上海临港  园区开发
    600895//1.00 张江高科  园区开发
    
    ));
    
    List<Integer> listCode_109_fcfw = new ArrayList<Integer>(Arrays.asList(
        9000005,//2.00  世纪星源  房产服务
        9000056,//3.00  皇庭国际  房产服务
        9000505,//4.00  *ST珠江 房产服务
        9000861,//5.00  海印股份  房产服务
        9002285,//6.00  世联行 房产服务
        9002818//7.00  富森美 房产服务
    ));
    
    
    List<Integer> listAll = new ArrayList<Integer>();

    
    

    //查全部
    if(flag.equals("1")){
      listAll.addAll(listCode_1_hq); listAll.addAll(listCode_2_zz); listAll.addAll(listCode_3_kwzp); listAll.addAll(listCode_4_ryhg);
      listAll.addAll(listCode_5_jydq); listAll.addAll(listCode_6_ylbj);listAll.addAll(listCode_7_jjyp);listAll.addAll(listCode_8_smdl);
      listAll.addAll(listCode_9_ggbz); listAll.addAll(listCode_10_wjxx); listAll.addAll(listCode_11_jdcy);
      listAll.addAll(listCode_12_hk);
      listAll.addAll(listCode_13_cb);
      listAll.addAll(listCode_14_yssb);
      listAll.addAll(listCode_15_dqsb);
      listAll.addAll(listCode_16_gcjx);
      listAll.addAll(listCode_17_dqyb);
      listAll.addAll(listCode_18_dxyy);
      listAll.addAll(listCode_19_ggjt);
      listAll.addAll(listCode_20_sw);
      listAll.addAll(listCode_21_gsgr);
      listAll.addAll(listCode_22_hjbh);
      listAll.addAll(listCode_23_ccwl);
      listAll.addAll(listCode_24_yh);
      listAll.addAll(listCode_25_zq);
      listAll.addAll(listCode_26_bx);
      listAll.addAll(listCode_27_dyjr);
      listAll.addAll(listCode_28_dlsb);
      listAll.addAll(listCode_29_txsb);
      listAll.addAll(listCode_30_bdt);
      listAll.addAll(listCode_31_yqj);
      listAll.addAll(listCode_32_rjfw);
      listAll.addAll(listCode_33_hlw);
      listAll.addAll(listCode_34_zhl);
      listAll.addAll(listCode_35_mtkc);
      listAll.addAll(listCode_36_jtjg);
      listAll.addAll(listCode_37_slfd);
      listAll.addAll(listCode_38_hldf);
      listAll.addAll(listCode_39_xxdl);
      listAll.addAll(listCode_40_sykc);
      listAll.addAll(listCode_41_syjg);
      listAll.addAll(listCode_42_symy);
      listAll.addAll(listCode_43_pg);
      listAll.addAll(listCode_44_tzg);
      listAll.addAll(listCode_45_gjg);
      listAll.addAll(listCode_46_t);
      listAll.addAll(listCode_47_l);
      listAll.addAll(listCode_47_yx);
      listAll.addAll(listCode_48_hj);
      listAll.addAll(listCode_49_xjs);
      listAll.addAll(listCode_50_hgyl);
      listAll.addAll(listCode_51_lyhf);
      listAll.addAll(listCode_52_sl);
      listAll.addAll(listCode_53_xj);
      listAll.addAll(listCode_54_yltl);
      listAll.addAll(listCode_55_tc);
      listAll.addAll(listCode_56_sn);
      listAll.addAll(listCode_57_bl);
      listAll.addAll(listCode_58_qtjc);
      listAll.addAll(listCode_59_zzy);
      listAll.addAll(listCode_60_yy);
      listAll.addAll(listCode_61_ly);
      listAll.addAll(listCode_62_sl);
      listAll.addAll(listCode_63_lyzh);
      listAll.addAll(listCode_64_fz);
      listAll.addAll(listCode_65_fs);
      listAll.addAll(listCode_66_rzp);
      listAll.addAll(listCode_67_ryl);
      listAll.addAll(listCode_68_sp);
      listAll.addAll(listCode_69_bj);
      listAll.addAll(listCode_70_pj);
      listAll.addAll(listCode_71_hhyj);
      listAll.addAll(listCode_72_qczc);
      listAll.addAll(listCode_73_qcpj);
      listAll.addAll(listCode_74_qcfw);
      listAll.addAll(listCode_75_mtc);
      listAll.addAll(listCode_76_hxzy);
      listAll.addAll(listCode_77_swzy);
      listAll.addAll(listCode_78_zcy);
      listAll.addAll(listCode_79_bh);
      listAll.addAll(listCode_80_csls);
      listAll.addAll(listCode_81_dqls);
      listAll.addAll(listCode_82_yysy);
      listAll.addAll(listCode_83_qtsy);
      listAll.addAll(listCode_84_spc);
      listAll.addAll(listCode_85_pfy);
      listAll.addAll(listCode_86_cpy);
      listAll.addAll(listCode_87_ysyx);
      listAll.addAll(listCode_88_lyfw);
      listAll.addAll(listCode_89_lyjd);
      listAll.addAll(listCode_90_jczz);
      listAll.addAll(listCode_91_jxjj);
      listAll.addAll(listCode_92_hgjx);
      listAll.addAll(listCode_93_qgjx);
      listAll.addAll(listCode_94_fzjx);
      listAll.addAll(listCode_95_lyjx);
      listAll.addAll(listCode_96_zyjx);
      listAll.addAll(listCode_97_tl);
      listAll.addAll(listCode_98_sy);
      listAll.addAll(listCode_99_ky);
      listAll.addAll(listCode_100_gl);
      listAll.addAll(listCode_101_lq);
      listAll.addAll(listCode_102_jc);listAll.addAll(listCode_103_gk);listAll.addAll(listCode_104_jzsg);listAll.addAll(listCode_105_jzzs);
      listAll.addAll(listCode_106_qgdc);listAll.addAll(listCode_107_qydc);listAll.addAll(listCode_108_yqkf); listAll.addAll(listCode_109_fcfw);
      
    }else if(flag.equals("2")){
      //查个行业
      listAll.addAll(listCode_4_ryhg);
      
      
    }else if(flag.equals("3")){
      //查某个股票 
      listAll.add(stockNum);  // 9000001   要加9在前面
      
    }
    
    
    

    return listAll;
    
    
  }
  
  
  //东财  扣非利润   http://f10.eastmoney.com/f10_v2/BackOffice.aspx?command=RptF10MainTarget&code=60051901&num=9&code1=sh600519&spstr=&n=1&timetip=1487063111207
}
