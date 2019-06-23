package cn.afterturn.boot.admin.controller;

import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;

import java.io.*;

public class YuQiTest {


    private static String sql = "INSERT INTO `res_ds_bank_no_dca_commission_day_coop`(`PARTITION_DATE`, `REPAY_DATE`, `CARD_NO`, `REF_NBR`, `DUE_DAYS`, `BANK_GROUP_ID`, `BANK_NO`, `BANK_PROPORTION`, `REPAY_AMT`, `COMMISSION_RATIO`, `OUT_EXPENSE`) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s');";

    public static void main(String[] args) throws IOException {
        File folder = new File("C:\\Users\\jueyue\\Desktop\\aaa");

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < folder.listFiles().length; i++) {
            File one = folder.listFiles()[i];
            one = one.listFiles()[0];
            for (int j = 0; j < one.listFiles().length; j++) {
                if (one.listFiles()[j].getName().equalsIgnoreCase("000000_0.gz")) {
                    System.out.println(one.listFiles()[j].getAbsoluteFile().toString());
                    unCompressArchiveGz(one.listFiles()[j]);


                    String fileName =
                            one.listFiles()[j].getName().substring(0, one.listFiles()[j].getName().lastIndexOf("."));

                    String finalName = one.listFiles()[j].getParent() + File.separator + fileName;
                    System.out.println(finalName);

                    BufferedReader br = new BufferedReader(new FileReader(new File(finalName)));
                    br.lines().forEach(line -> {
                        String[] fields = line.split("\u0001");
                        sb.append(String.format(sql, fields));
                        sb.append("\r\n");
                    });
                }
            }
        }
        File sqlFile = new File("C:\\Users\\jueyue\\Desktop\\aaa\\sql.sql");
        FileWriter fileWritter = new FileWriter(sqlFile, true);
        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
        bufferWritter.write(sb.toString());
        bufferWritter.close();

    }


    /**
     * 解压
     *
     * @throws IOException
     * @author yutao
     * @date 2017年5月27日下午4:03:29
     */
    private static void unCompressArchiveGz(File file) throws IOException {

        BufferedInputStream bis =
                new BufferedInputStream(new FileInputStream(file));

        String fileName =
                file.getName().substring(0, file.getName().lastIndexOf("."));

        String finalName = file.getParent() + File.separator + fileName;

        BufferedOutputStream bos =
                new BufferedOutputStream(new FileOutputStream(finalName));

        GzipCompressorInputStream gcis =
                new GzipCompressorInputStream(bis);

        byte[] buffer = new byte[1024];
        int read = -1;
        while ((read = gcis.read(buffer)) != -1) {
            bos.write(buffer, 0, read);
        }
        gcis.close();
        bos.close();
    }


}