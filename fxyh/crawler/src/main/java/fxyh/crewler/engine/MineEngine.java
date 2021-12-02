package fxyh.crewler.engine;

import com.geccocrawler.gecco.request.HttpRequest;
import fxyh.crewler.annoation.Attr;
import fxyh.crewler.annoation.Mine;
import fxyh.crewler.annoation.MineDo;
import fxyh.crewler.annoation.Path;
import lombok.Data;
import lombok.extern.java.Log;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Data
@Log
public class MineEngine {
    private List<HttpRequest> startRequests = new ArrayList<HttpRequest>();
    public String name;
    private HttpClient client = new DefaultHttpClient();
    private HttpGet post;
    private String  url;
    public static MineEngine create(String url) {
        MineEngine mineEngine = new MineEngine(url);
        mineEngine.setName("大地雷");
        return mineEngine;
    }
    public MineEngine(String url1){
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000)//一、连接超时：connectionTimeout-->指的是连接一个url的连接等待时间
                .setSocketTimeout(5000)// 二、读取数据超时：SocketTimeout-->指的是连接上一个url，获取response的返回等待时间
                .setConnectionRequestTimeout(5000)
                .build();
        HttpClient client = new DefaultHttpClient();
        url=url1;
        post = new HttpGet(url);
        post.setConfig(requestConfig);
        post.setHeader("User-Agent","Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36");
    }
    public Object load() throws IllegalAccessException, InstantiationException, IOException, ClassNotFoundException, InvocationTargetException {
        Reflections reflections=new Reflections("fxyh.crewler.bean",Arrays.asList(new SubTypesScanner(false) ,new TypeAnnotationsScanner(),new FieldAnnotationsScanner(),new MethodAnnotationsScanner()));
        /*获取所有爬虫注解，必须以@Mine注解*/
        for(Class clazz:reflections.getTypesAnnotatedWith(Mine.class)) {
            Class type = clazz.getClass().getClass();
            Object z = clazz.newInstance();
            if(url.contains(z.getClass().getDeclaredAnnotation(Mine.class).crewlerUrl())){
                HttpResponse response = client.execute(post);
                String result = EntityUtils.toString(response.getEntity());
                Document doc = Jsoup.parse(result);
                Object obj=objToCrewler(z, doc);
                log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~11111111111111111111111111~");
                log.info(obj.toString());
                for(Method method:z.getClass().getMethods()){
                    if(method.getDeclaredAnnotation(MineDo.class)!=null){
                       return  method.invoke(z,obj);
                    }
                }
            }
        }
        return null;
    }
    public  Object objToCrewler(Object crewlerClass , Document doc) throws IllegalAccessException, InstantiationException, ClassNotFoundException {

        for (Field field : crewlerClass.getClass().getDeclaredFields()) {
            //获取所有指定注释的字段
            if (field.getDeclaredAnnotation(Path.class) != null) {
                    /*
                      判断是否为List类型,当指定path含有多个值时，例如抓取文章等信息，首先需要通过List接受，通过List中泛型的类型再次指定各个path
                     */
                if (field.getType().getClass().isPrimitive()||field.getType().getSimpleName().equals("String") ) {
                    /*/
                      根据匹配规则赋值
                     */
                    field.setAccessible(true);
                    if(field.getAnnotation(Attr.class)!=null){
                        field.set(crewlerClass, doc.select(field.getDeclaredAnnotation(Path.class).cssPath()).attr(field.getDeclaredAnnotation(Attr.class).value()));
                    }else  if(field.getAnnotation(Path.class)!=null){
                        field.set(crewlerClass, doc.select(field.getDeclaredAnnotation(Path.class).cssPath()).text());
                    }
                } else if (field.getType().isAssignableFrom(List.class)) {
                    /*/
                      如果类型为list，说明为嵌套格式，递归调用。
                     */
                    if (field.getGenericType() instanceof ParameterizedType) {
                        Class listFileType=Class.forName(((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0].getTypeName());
                        ParameterizedType fieldType= (ParameterizedType) field.getGenericType();

                        List fileList=new ArrayList();
                        for(Element e:doc.select(field.getDeclaredAnnotation(Path.class).cssPath())){
//                            log.info(e.html());
                            //listClass = objToCrewler(listClass, Jsoup.parse(e.html()));
                           // log.info("listClass"+listClass.toString());
                            Object listClass = listFileType.newInstance();
                            fileList.add(objToCrewler(listClass, Jsoup.parse(e.html())));
                            log.info("forfileList"+fileList.toString());
                        }
                        log.info("fileList"+fileList.toString());
                        field.set(crewlerClass, fileList);
                        log.info("field"+field.getName());
                        log.info("crewlerClass"+crewlerClass.toString());
                    }
                }
            }
        }
        log.info("crewlerClass.toString()"+crewlerClass.toString());
        return crewlerClass;
    }


    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException, IOException, InvocationTargetException {
        int nowTime = (int) (new Date().getTime() / 1000);
        int startTime = nowTime - 86400;
        String url = String.format("%s%s", String.valueOf(startTime), String.valueOf(nowTime));
        url = "https://www.baidu.com/s?wd=阜新银行&gpc=stf%" + startTime + "%" + nowTime;
        log.info(url);
        MineEngine mineEngine=new MineEngine(url);
        mineEngine.load();
        }

}
