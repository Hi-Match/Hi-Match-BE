package kr.co.himatch.thanksyouplz.code.util;

import java.util.Arrays;
import java.util.List;

public class PersonalTypeConstants {
    public static final List<PersonalTypeVO> NDCOList = Arrays.asList(
            new PersonalTypeVO(
                    "",
                    Arrays.asList("")
            ));
    public static final List<PersonalTypeVO> NDCSList = Arrays.asList(
            new PersonalTypeVO(
                    "",
                    Arrays.asList("")
            ));
    public static final List<PersonalTypeVO> NDLOList = Arrays.asList(
            new PersonalTypeVO(
                    "",
                    Arrays.asList("")
            ));
    public static final List<PersonalTypeVO> NDLSList = Arrays.asList(
            new PersonalTypeVO(
                    "",
                    Arrays.asList("")
            ));
    public static final List<PersonalTypeVO> NBCOList = Arrays.asList(
            new PersonalTypeVO(
                    "",
                    Arrays.asList("")
            ));
    public static final List<PersonalTypeVO> NBCSList = Arrays.asList(
            new PersonalTypeVO(
                    "",
                    Arrays.asList("")
            ));
    public static final List<PersonalTypeVO> NBLOList = Arrays.asList(
            new PersonalTypeVO(
                    "",
                    Arrays.asList("")
            ));
    public static final List<PersonalTypeVO> NBLSList = Arrays.asList(
            new PersonalTypeVO(
                    "",
                    Arrays.asList("")
            ));
    public static final List<PersonalTypeVO> FDCOList = Arrays.asList(
            new PersonalTypeVO(
                    "",
                    Arrays.asList("")
            ));
    public static final List<PersonalTypeVO> FDCSList = Arrays.asList(
            new PersonalTypeVO(
                    "",
                    Arrays.asList("")
            ));
    public static final List<PersonalTypeVO> FDLOList = Arrays.asList(
            new PersonalTypeVO(
                    "",
                    Arrays.asList("")
            ));
    public static final List<PersonalTypeVO> FDLSList = Arrays.asList(
            new PersonalTypeVO(
                    "",
                    Arrays.asList("")
            ));
    public static final List<PersonalTypeVO> FBCOList = Arrays.asList(
            new PersonalTypeVO(
                    "",
                    Arrays.asList("")
            ));
    public static final List<PersonalTypeVO> FBCSList = Arrays.asList(
            new PersonalTypeVO(
                    "",
                    Arrays.asList("")
            ));
    public static final List<PersonalTypeVO> FBLOList = Arrays.asList(
            new PersonalTypeVO(
                    "",
                    Arrays.asList("")
            ));
    public static final List<PersonalTypeVO> FBLSList = Arrays.asList(
            new PersonalTypeVO(
                    "",
                    Arrays.asList("")
            ));


    public static List<PersonalTypeVO> fromCodeToInfo(PersonalTypeEnum typeEnum) {
        List<PersonalTypeVO> info = null;
        switch (typeEnum) {
            case NDCO:
                info = NDCOList;
                break;
            case NDCS:
                info = NDCSList;
                break;
            case NDLO:
                info = NDLOList;
                break;
            case NDLS:
                info = NDLSList;
                break;
            case NBCO:
                info = NBCOList;
                break;
            case NBCS:
                info = NBCSList;
                break;
            case NBLO:
                info = NBLOList;
                break;
            case NBLS:
                info = NBLSList;
                break;
            case FDCO:
                info = FDCOList;
                break;
            case FDCS:
                info = FDCSList;
                break;
            case FDLO:
                info = FDLOList;
                break;
            case FDLS:
                info = FDLSList;
                break;
            case FBCO:
                info = FBCOList;
                break;
            case FBCS:
                info = FBCSList;
                break;
            case FBLO:
                info = FBLOList;
                break;
            case FBLS:
                info = FBLSList;
                break;
        }
        return info;
    }


}
