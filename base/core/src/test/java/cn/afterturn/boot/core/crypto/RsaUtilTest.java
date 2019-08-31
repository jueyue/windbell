package cn.afterturn.boot.core.crypto;


public class RsaUtilTest {

    @org.junit.Test
    public void rsa256Sign() {
        System.out.println(RsaUtil.rsa256Sign("123456","MIIEowIBAAKCAQEAujvGcY4xgCoU58ucVSe77vqZPvZuLSrhsa5dgAi82qaEOst2" +
                "QyxDxMH70738aT8hSD/gdvuY7P0bko9S/lo7Qboo83Oh/isJbpC6ubAaglEAqsLH" +
                "4XsHidK2sdYJXnIzp/pKSY9GLE10mGjvGrtlcAzfEMFlQMd3FGYYkqk/7QRTMBpA" +
                "/XPMFjIaq3DRtWiU9vS3N0KUbJNXekSSK1xtBzh/VxQphmi8HZVCyK+YK2UaTa/y" +
                "ztIln6zcSSOl/ZseomzAh0w0H8LUiyNqg4YGAxtIdd6MG/mFaZ6hiLVulrGQlLPV" +
                "1L5cSakD1Uk5dFBIEflQBNgkQvx5kyKYHK34HwIDAQABAoIBABYOklhMsQwu777s" +
                "XJ2xUQ7i21ebtDzhQU3cCvLpFgUHh82231DhYz1ghET10XldosF930X4UopvB975" +
                "3qiFY1NLiyIaZetGiZ1wwszPULRyh5Ox8h6AhcoLDNxPL1jiSb/xYu8HaxZALjkG" +
                "5JrT6xW0UI8ZeLaNaUsfNHXaWdnmX3e5Hum/u2DVOJynLZIOi7RZidBqr4Q/7TXM" +
                "K8GXzSc2U0tJisJC8Tc0OIii8wFjHBWpSiYXBnIaRbW17UKow3h8zd2Ak23lAmoO" +
                "wg/Oh3JUqhLHL+qzckJWQ1Q1WIuXh1B/Dn8CgB541UdHoWS0KLEB9LNBoCOT0xpS" +
                "KFO6MSECgYEA450+cqTfbrj9Iy4E3eooVzAbqoUy8zFDuHlceOIwJxno3wWPUvu0" +
                "7C6w4C40S1VL7JMIsLmXtkoLYbIKB/+Tp/HmNj008EZGEEqn1t+sM3IrqiSMA1D1" +
                "4hHiiWu48gP55966s5kxsI6Dl5EuiwITmIfA1t40ORsz0tQWwv95FJkCgYEA0XVr" +
                "hF+dwpkD19UG0xoSeKbQqr4ugJ2MIF1oA7lS1ic+Qu2ws5aV4e+i5Kcp2TFz1A+R" +
                "TIaDTkAJkt1tjiRaPfySNDLKpujXlizJilIan0j3pwsH6HExx7H0PTwIRN60IhSu" +
                "080YeU2/yHpLmnLYcV1pKcIaV0tF01UAy56KrXcCgYEA2pIEBqkmWbfQdXjaeZNC" +
                "2765wnyYGYpkQoBf+UETBmlOAuPe5swIx4hql0wnMXQeLXZgMPx3oDfoSA9krJuT" +
                "WgHjlJAgBt8kpr5Z/+/X96saHQ9tWD3lcArGpDnKZrTExRFP7ekdGzMvUFu8+wWq" +
                "dZ1e47aLJY9mq3y4wtX6mnkCgYBecWFPSIWrrq7K7KStHr5hZGYF9sak+2SpWzSB" +
                "KViucvdh2iaocyeVviVmj/EQm3msjnBnXudpqvomHG0/gCiodm+91YEGjUpXzk35" +
                "blfOpmn0CcdBCXj2n8iOq7jWxMvX6heCLKHyYBovXOUl+M6bHxFB4OFbpvcO+O7o" +
                "4M2KtQKBgEqTI8/hm7t59Cd+5VtdSTLNmxNnNelY97ClQ8BMoD3MKG5/1zRzwxyE" +
                "fDWrPKBVkGjqzKp+VmrhAUPBooyyyTbtHTM+PqpaUdVOztD6eaPuGOZxpY8MWOdq" +
                "tC1Z2UCv1XY8wqHMALKq/1b29XSrFMKfZy6tZY2wCoHVHjURARyI"));
    }

    @org.junit.Test
    public void rsa256CheckContent() {
        RsaUtil.rsa256CheckContent("123456","test","MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAujvGcY4xgCoU58ucVSe7" +
                "7vqZPvZuLSrhsa5dgAi82qaEOst2QyxDxMH70738aT8hSD/gdvuY7P0bko9S/lo7" +
                "Qboo83Oh/isJbpC6ubAaglEAqsLH4XsHidK2sdYJXnIzp/pKSY9GLE10mGjvGrtl" +
                "cAzfEMFlQMd3FGYYkqk/7QRTMBpA/XPMFjIaq3DRtWiU9vS3N0KUbJNXekSSK1xt" +
                "Bzh/VxQphmi8HZVCyK+YK2UaTa/yztIln6zcSSOl/ZseomzAh0w0H8LUiyNqg4YG" +
                "AxtIdd6MG/mFaZ6hiLVulrGQlLPV1L5cSakD1Uk5dFBIEflQBNgkQvx5kyKYHK34" +
                "HwIDAQAB");
    }

    @org.junit.Test
    public void rsaDecrypt() {
    }

    @org.junit.Test
    public void rsaEncrypt() {
    }
}