package com.jzfq.rms.product.persistence.cache.redis;

import com.jzfq.rms.product.bean.Dictionary;
import com.jzfq.rms.product.utils.ImportExeclUtils.ImportExeclUtil;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author 大连桔子分期科技有限公司
 * @date 2018/2/9 11:45
 */
public class TestImportExcel {


    public static void main(String[] args) throws IOException, Exception {

        String fileName = "桔盾重构初始化数据_2018-2-9.xlsx";
        String filePath = "C:\\work\\文档\\SQL\\桔盾风控平台\\桔盾重构初始化数据_2018-2-9.xlsx";
        InputStream in = new FileInputStream(new File(filePath));
        Workbook wb = ImportExeclUtil.chooseWorkbook(fileName, in);

        //读取对象列表的信息
        Dictionary dictionary = new Dictionary();
        //第二行开始，到倒数第三行结束（总数减去两行）
        List<Dictionary> readDateListT = ImportExeclUtil.readDateListT(wb, dictionary, 3);
        System.out.println(readDateListT);

    }

}
