package io.pragra.api.framework.data;

import io.pragra.api.framework.config.Config;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class ExcelProvider {

    private Workbook workbook = null;
    private List<Map<String, Object>> data = new ArrayList<>();

    public ExcelProvider() {
        System.out.println("Location "+ Config.getProperty("datafileName"));
        try(InputStream stream = new FileInputStream(Config.getProperty("datafileName"))){
            workbook = new XSSFWorkbook(stream);
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public List<Map<String, Object>> getAPiData(String apiSheet){
        Sheet sheet = workbook.getSheet(apiSheet);
        Iterator<Row> rowIterator = sheet.rowIterator();
        Row headRow = rowIterator.next();
        while (rowIterator.hasNext()){
            Row row = rowIterator.next();
            Map<String,Object> daMap = new HashMap<>();
            Map<String,Object> validationMap = new HashMap<>();

            for (int i = 0; i<row.getLastCellNum();i++){
                if(headRow.getCell(i).getStringCellValue().equals("PathParams")){

                    if(null!= row.getCell(i)){
                        daMap.put("PathParams",getPathParam((String) getCellData(row.getCell(i))));
                    }

                }
                if(headRow.getCell(i).getStringCellValue().equals("StatusCode")){
                    if(null!=row.getCell(i))
                    daMap.put("statusCode",getCellData(row.getCell(i)));
                }

                if(headRow.getCell(i).getStringCellValue().contains("Data")){
                    if(null!=row.getCell(i))
                        daMap.put("statusCode",getCellData(row.getCell(i)));
                }
            }
            data.add(daMap);


        }
        return data;
    }

    private Map<String,Object> getPathParam(String cellValue){
        Map<String, Object> pathParam = new HashMap<>();

        String[] data = cellValue.split("\\|");
        for (String token: data){
           String[] param =  token.split("=");
           pathParam.put(param[0],param[1]);
        }
        return pathParam;
    }

    private Map<String,Object> getQueryParam(String cellValue){
        Map<String, Object> queryParam = new HashMap<>();
        if (null==cellValue){
            return Collections.singletonMap("","");
        }
        String[] data = cellValue.split("\\|");
        for (String token: data){
            String[] param =  token.split("=");
            queryParam.put(param[0],param[1]);
        }
        return queryParam;
    }

//    private Map<String, Object> getData(Map<String, Object> dataMap, String data){
//
//    }

    private Object getCellData(Cell cell){
        switch (cell.getCellTypeEnum()){
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return cell.getNumericCellValue();
            case BLANK:
                return cell.getStringCellValue();
            case _NONE:
                return cell.getStringCellValue();
            case BOOLEAN:
                return cell.getBooleanCellValue();
            default:
                return cell.getStringCellValue();
        }
    }



}
