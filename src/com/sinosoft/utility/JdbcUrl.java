package com.sinosoft.utility;

public class JdbcUrl
{
    // @Field
    private String DBType;
    private String IP;
    private String Port;
    private String DBName;
    private String ServerName;
    private String UserName;
    private String PassWord;


    // @Constructor
    public JdbcUrl()
    {
    	DBType = "SQLITE";

        IP=this.getClass().getClassLoader().getResource("/").getPath();
        IP=IP.replace("classes","");
        IP=IP.replace("%20"," ");
        IP+="DataBase.db3";
     
        Port = "";
        DBName = "";
        UserName = "";
        PassWord = "";  
 
    }


    // @Method
    public String getDBType()
    {
        return DBType;
    }

    public String getIP()
    {
        return IP;
    }

    public String getPort()
    {
        return Port;
    }

    public String getDBName()
    {
        return DBName;
    }

    public String getServerName()
    {
        return ServerName;
    }

    public String getUserName()
    {
        return UserName;
    }

    public String getPassWord()
    {
        return PassWord;
    }

    public void setDBType(String aDBType)
    {
        DBType = aDBType;
    }

    public void setIP(String aIP)
    {
        IP = aIP;
    }

    public void setPort(String aPort)
    {
        Port = aPort;
    }

    public void setDBName(String aDBName)
    {
        DBName = aDBName;
    }

    public void setServerName(String aServerName)
    {
        ServerName = aServerName;
    }

    public void setUser(String aUserName)
    {
        UserName = aUserName;
    }

    public void setPassWord(String aPassWord)
    {
        PassWord = aPassWord;
    }


    /**
     * 获取连接句柄
     * @return String
     */
    public String getJdbcUrl()
    {
//        String sUrl = "";
        StringBuffer sUrl = new StringBuffer(256);
        //Oracle连接句柄
        if (DBType.trim().toUpperCase().equals("ORACLE"))
        {
//            sUrl = "jdbc:oracle:thin:@" + IP + ":"
//                   + Port + ":"
//                   + DBName;
            sUrl.append("jdbc:oracle:thin:@");
            sUrl.append(IP);
            sUrl.append(":");
            sUrl.append(Port);
            sUrl.append(":");
            sUrl.append(DBName);
        }
        //InforMix连接句柄
        if (DBType.trim().toUpperCase().equals("INFORMIX"))
        {
//            sUrl = "jdbc:informix-sqli://" + IP + ":"
//                   + Port + "/"
//                   + DBName + ":"
//                   + "informixserver=" + ServerName + ";"
//                   + "user=" + UserName + ";"
//                   + "password=" + PassWord + ";";
            sUrl.append("jdbc:informix-sqli://");
            sUrl.append(IP);
            sUrl.append(":");
            sUrl.append(Port);
            sUrl.append(DBName);
            sUrl.append(":");
            sUrl.append("informixserver=");
            sUrl.append(ServerName);
            sUrl.append(";");
            sUrl.append("user=");
            sUrl.append(UserName);
            sUrl.append(";");
            sUrl.append("password=");
            sUrl.append(PassWord);
            sUrl.append(";");
        }
        //SqlServer连接句柄
        if (DBType.trim().toUpperCase().equals("SQLSERVER"))
        {
//            sUrl = "jdbc:inetdae:" + IP + ":"
//                   + Port + "?sql7=true&database=" + DBName + "&charset=UTF-8";
            sUrl.append("jdbc:inetdae:");
            sUrl.append(IP);
            sUrl.append(":");
            sUrl.append(Port);
            sUrl.append("?sql7=true&database=");
            sUrl.append(DBName);
            sUrl.append("&charset=UTF-8");
        }
        //WebLogicPool连接句柄
        if (DBType.trim().toUpperCase().equals("WEBLOGICPOOL"))
        {
//            sUrl = "jdbc:weblogic:pool:" + DBName;
            sUrl.append("jdbc:weblogic:pool:");
            sUrl.append(DBName);
        }
        //DB2连接句柄
        if (DBType.trim().toUpperCase().equals("DB2"))
        {
//            sUrl = "jdbc:db2://" + IP + ":"
//                   + Port + "/"
//                   + DBName;
            sUrl.append("jdbc:db2://");
            sUrl.append(IP);
            sUrl.append(":");
            sUrl.append(Port);
            sUrl.append("/");
            sUrl.append(DBName);
        } 
        //SQLITE连接句柄
        if (DBType.trim().toUpperCase().equals("SQLITE"))
        {
            sUrl.append("jdbc:sqlite:");
            sUrl.append(IP);
        }
        return sUrl.toString();
    }
}
