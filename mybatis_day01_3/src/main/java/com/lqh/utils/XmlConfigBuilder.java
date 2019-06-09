package com.lqh.utils;

import com.lqh.domain.Configuration;
import com.lqh.domain.Mapper;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlConfigBuilder {
    // 1.解析核心配置文件,将数据封装到cfg中
    public static Configuration buildConfigration(InputStream is) throws DocumentException {
        Configuration cfg = new Configuration();
        SAXReader saxReader = new SAXReader();
        Document doc = saxReader.read(is);
        Element root = doc.getRootElement();
        List<Element> list = root.selectNodes("//property");
        for (Element e : list) {
            String name = e.attributeValue("name");
            String value = e.attributeValue("value");
            //判断
            if ("driver".equals(name)) {
                cfg.setDriver(value);
            }
            if ("url".equals(name)) {
                cfg.setUrl(value);
            }
            if ("username".equals(name)) {
                cfg.setUsername(value);
            }
            if ("password".equals(name)) {
                cfg.setPassword(value);
            }
        }
        // 对sql 的map集合赋值
        Map<String, Mapper> mappers = new HashMap();
        // 解析sql语句的配置文件
        Element mapperElement = root.element("mappers");
        if (mapperElement != null) {
            //如果mappers标签存在,获取它下面所有的子元素mapper
            List<Element> mapper = mapperElement.elements("mapper");
            for (Element element : mapper) {
                //获取sql配置文件的路径
                String path = element.attributeValue("resource");
                //将每一个sql配置文件中的map集合存入大的map集合中
                mappers.putAll(XmlConfigBuilder.loadXmlForMapper(path));
            }
        }
        cfg.setMappers(mappers);
        return cfg;
    }

    /**
     * 解析sql语句的配置文件
     *
     * @param path
     * @return
     */
    private static Map<String, Mapper> loadXmlForMapper(String path) throws DocumentException {
        Map<String, Mapper> map = new HashMap<String, Mapper>();
        // 1.使因为有sql配置文件的路径,所以用类加载器去加载sql的配值文件
        InputStream is = XmlConfigBuilder.class.getClassLoader().getResourceAsStream(path);
        // 2.使用Dom4j解析
        SAXReader saxReader = new SAXReader();
        Document doc = saxReader.read(is);
        Element rootElement = doc.getRootElement();
        // 3.获取元素内容(文本,属性值)添加到集合
        String namespace = rootElement.attributeValue("namespace"); //接口的全限定名
        List<Element> select = rootElement.elements("select");
        for (Element selectElement : select) {
            //获取id
            String id = selectElement.attributeValue("id");
            //获取resultType
            String resultType = selectElement.attributeValue("resultType");
            //获取sql语句
            String sql = selectElement.getText();
            //创建mapper对象
            Mapper mapper = new Mapper();
            mapper.setQuerySql(sql);
            mapper.setResultType(resultType);
            map.put(namespace + "." + id, mapper);   //唯一标识
        }
        return map;
    }
}
