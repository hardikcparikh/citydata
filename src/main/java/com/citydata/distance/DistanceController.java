package com.citydata.distance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

@RestController
public class DistanceController {

    final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final String YES="Yes";
    private final String NO="No";

    @Autowired
    ResourceLoader resourceLoader;

    @RequestMapping("/connected")
    public ResponseEntity<Object> connected(@RequestParam("origin") String origin, @RequestParam("destination") String destination)
    {
        String result=null;
        HashMap<String,String> cityDataMap = loadCityFileData();
        if(cityDataMap.containsKey(origin) || cityDataMap.containsValue(origin))
        {
            if((cityDataMap.get(origin)!=null && cityDataMap.get(origin).equalsIgnoreCase(destination)) ||
                    (getKeyByValue(cityDataMap,origin)!=null && getKeyByValue(cityDataMap,origin).equalsIgnoreCase(destination)))
                result=YES;
            else
                result=NO;
        }
        else
        {
            result=NO;
        }
        return new ResponseEntity<Object>(result, HttpStatus.OK);
    }
    public  String getKeyByValue(Map<String, String> map, String value) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey() ;
            }
        }
        return null;
    }
    private HashMap<String,String> loadCityFileData()
    {
        Resource resource = resourceLoader.getResource("classpath:city.txt");
        HashMap<String,String> cityDataMap=new HashMap<>();
        try
        {
            ArrayList<HashSet> cityZoneList=new ArrayList<>();
            InputStream inputStream = resource.getInputStream();
            String data = new String(FileCopyUtils.copyToByteArray(inputStream), StandardCharsets.UTF_8);
            StringTokenizer st= new StringTokenizer(data,"\n");
            while (st.hasMoreElements()) {
                String line= st.nextElement().toString();
                StringTokenizer stLine= new StringTokenizer(line,",");
                cityDataMap.put(stLine.nextToken().trim(),stLine.nextToken().trim());

            }
            LOGGER.info(data);
        }
        catch (IOException e)
        {
            LOGGER.error("IOException", e);
        }
         return cityDataMap;
    }

}
