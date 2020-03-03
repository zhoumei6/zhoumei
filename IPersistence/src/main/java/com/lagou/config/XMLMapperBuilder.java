package com.lagou.config;

import com.lagou.pojo.Configuration;
import com.lagou.pojo.MappedStatement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

public class XMLMapperBuilder {

    private Configuration configuration;

    public XMLMapperBuilder(Configuration configuration) {
        this.configuration =configuration;
    }

    public void parse(InputStream inputStream) throws DocumentException {

        Document document = new SAXReader().read(inputStream);
        Element rootElement = document.getRootElement();
d
        String namespace = rootElement.attributeValue("namespace");
        List<Element> selectlist = rootElement.selectNodes("//select");
        Elementforeach(selectlist,namespace);

        List<Element> updatelist = rootElement.selectNodes("//update");
        Elementforeach(updatelist,namespace);

        List<Element> deletelist = rootElement.selectNodes("//delete");
        Elementforeach(deletelist,namespace);

        List<Element> deletelist = rootElement.selectNodes("//save");
        Elementforeach(deletelist,namespace);
    }


        public void Elementforeach(List<Element> list,String namespace){
            for (Element element : list) {
                String id = element.attributeValue("id");
                String resultType = element.attributeValue("resultType");
                String paramterType = element.attributeValue("paramterType");
                String sqlText = element.getTextTrim();
                MappedStatement mappedStatement = new MappedStatement();
                mappedStatement.setId(id);
                mappedStatement.setResultType(resultType);
                mappedStatement.setParamterType(paramterType);
                mappedStatement.setSql(sqlText);
                String key = namespace+"."+id;
                configuration.getMappedStatementMap().put(key,mappedStatement);
            }
        }



    }
