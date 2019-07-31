package com.tcsoft.searchmachinary.config;


public class Config {

    //socket
    public static final String IP_SOCKET = "";
    public static final int PORT_SOCKET = 8084;


    //opac
    //public static final String PATH_OPAC = "http://10.10.6.98:28098/opac";
    public static final String PATH_OPAC = "http://j.tcsoft.info:28098/opac";

    //openlib
    //public static final String PATH_OPEN_LIB = "http://10.10.6.98:28098/openlib";
    public static final String PATH_OPEN_LIB = "http://j.tcsoft.info:28098/openlib";


    //consult
    public static final String PATH_CONSULT = "http://rc.interlib.com.cn:82/rc/web/api/kb/wx/getkblist.html";


    //weather
    public static final String PATH_WEATHER = "http://co.moji.com/api/weather2/weather";

    //cover
    public static final String PATH_BOOK_COVER = "https://book-resource.dataesb.com/websearch/metares";

    //openlib id
    public static final String ID_OPEB_LIB = "atmopeninterface";
    //openlib password
    public static final String PASS_OPEN_LIB = "b4e8d12b5bca263f9b1ec36835d29c108";
    //file
    public static final String DIRS_CONFIG = "/tcsoft/config/";
    public static final String FILE_CONFIG = "Config.txt";
}
