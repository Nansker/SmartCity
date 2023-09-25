package com.nansk.smartcity.design;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/17 20:34
 * @description 资源
 */

import com.nansk.smartcity.R;

import java.util.ArrayList;
import java.util.List;


public class DesignResources {

    public static List<Integer> getPartyBannerPagers() {
        List<Integer> pagers = new ArrayList<>();
        pagers.add(R.drawable.party_banner1);
        pagers.add(R.drawable.party_banner2);
        pagers.add(R.drawable.party_banner3);
        pagers.add(R.drawable.party_banner4);
        return pagers;
    }

    public static List<Integer> getSupportBannerPagers() {
        List<Integer> pagers = new ArrayList<>();
        pagers.add(R.drawable.support_banner1);
        pagers.add(R.drawable.support_banner2);
        pagers.add(R.drawable.support_banner3);
        pagers.add(R.drawable.support_banner4);
        pagers.add(R.drawable.support_banner5);
        return pagers;
    }
    public static int getSupportVillage(int id) {
        int img;
        switch (id) {
            case 1:
                img = R.drawable.support_village1;
                break;
            case 2:
                img = R.drawable.support_village2;
                break;
            case 3:
                img = R.drawable.support_village3;
                break;
            case 4:
                img = R.drawable.support_village4;
                break;
            case 5:
                img = R.drawable.support_village5;
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + id);
        }
        return img;
    }
    public static List<Integer> getCommunityBannerPagers() {
        List<Integer> pagers = new ArrayList<>();
        pagers.add(R.drawable.community_banner1);
        pagers.add(R.drawable.community_banner2);
        pagers.add(R.drawable.community_banner3);
        pagers.add(R.drawable.community_banner4);
        pagers.add(R.drawable.community_banner5);
        pagers.add(R.drawable.community_banner6);
        return pagers;
    }

    public static List<Integer> getPensionBannerPagers() {
        List<Integer> pagers = new ArrayList<>();
        pagers.add(R.drawable.pension_banner1);
        pagers.add(R.drawable.pension_banner2);
        pagers.add(R.drawable.pension_banner3);
        pagers.add(R.drawable.pension_banner4);
        pagers.add(R.drawable.pension_banner4);
        return pagers;
    }

    public static int getPensionOrgImage(int id) {
        int img;
        switch (id) {
            case 1:
                img = R.drawable.pension_org1;
                break;
            case 2:
                img = R.drawable.pension_org2;
                break;
            case 3:
                img = R.drawable.pension_org3;
                break;
            case 4:
                img = R.drawable.pension_org4;
                break;
            case 5:
                img = R.drawable.pension_org5;
                break;
            case 6:
                img = R.drawable.pension_org6;
                break;
            case 7:
                img = R.drawable.pension_org7;
                break;
            case 8:
                img = R.drawable.pension_org8;
                break;
            case 9:
                img = R.drawable.pension_org9;
                break;
            case 10:
                img = R.drawable.pension_org10;
                break;
            case 11:
                img = R.drawable.pension_org11;
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + id);
        }
        return img;
    }


    public static int getSupportPressImage(int id) {
        int img;
        switch (id) {
            case 1:
                img = R.drawable.support_press1;
                break;
            case 2:
                img = R.drawable.support_press2;
                break;
            case 3:
                img = R.drawable.support_press4;
                break;
            case 4:
                img = R.drawable.support_press5;
                break;
            case 5:
                img = R.drawable.support_press6;
                break;
            case 6:
                img = R.drawable.support_press7;
                break;
            case 7:
                img = R.drawable.support_press8;
                break;
            case 8:
                img = R.drawable.support_press9;
                break;
            case 9:
                img = R.drawable.support_banner1;
                break;
            case 10:
                img = R.drawable.pension_org10;
                break;
            case 11:
                img = R.drawable.pension_org11;
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + id);
        }
        return img;
    }

    public static int getPartyPressImage(int id) {
        int img;
        switch (id) {
            case 1:
                img = R.drawable.party_press1;
                break;
            case 2:
                img = R.drawable.party_press2;
                break;
            case 3:
                img = R.drawable.party_press3;
                break;
            case 4:
                img = R.drawable.party_press4;
                break;
            case 5:
                img = R.drawable.party_press6;
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + id);
        }
        return img;
    }

}
