package com.example.myproject.base;

import com.example.myproject.enumBean.BaseEnum;
import com.example.myproject.enumBean.Gender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class xxx {

    private static final Logger logger = LoggerFactory.getLogger(xxx.class);

    public static void main(String[] strings) {

        logger.info(Gender.FEMALE.getText());
        logger.info(Gender.MALE.getStatus() + "");
        logger.info(BaseEnum.gender.FEMALE.getName());
        logger.info(BaseEnum.adType.CATEGORY_LIST.toString());
    }


}
