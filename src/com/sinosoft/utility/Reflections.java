package com.sinosoft.utility;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Vector;
import com.sinosoft.lis.log.*;

public class Reflections
{
    Vector mVResult = new Vector();

    /**
     * 构造函数显示
     * @param c1 Class
     */
    public static void printConstructors(Class c1)
    {
        Constructor[] constructors = c1.getDeclaredConstructors();
//    log.addlog ("------------------print Constructors-----------------");

        for (int i = 0; i < constructors.length; i++)
        {
            Constructor c = constructors[i];
            String name = c.getName();
//      System.out.print("   " + name+ "(");

            Class[] paramTypes = c.getParameterTypes();
//      for (int j = 0 ; j<paramTypes.length; j++)
//                   {
//        if (j > 0)
//                   System.out.print("Par, ");
//      }
//      log.addlog(");");
        }
    }

    /**
     * 方法显示
     * @param c1 Class
     */
    public static void printMethods(Class c1)
    {
        Method[] methods = c1.getDeclaredMethods();
//    log.addlog ("------------------print methods ----------------");

        for (int i = 0; i < methods.length; i++)
        {
            Method m = methods[i];
//            Class retType = m.getReturnType();
//            String name = m.getName();

//      System.out.print(Modifier.toString(m.getModifiers()));
//      log.addlog(" | " + retType.getName() + " |" + name + "(");

//            Class[] paramTypes = m.getParameterTypes();
//      for ( int j = 0 ; j < paramTypes.length; j++)
//      {
//        if(j > 0)
//          System.out.print(", ");
//        log.addlog(paramTypes[j].getName());
//      }
//      log.addlog("):");
        }
    }

    /**
     * 变量参数显示
     * @param c1 Class
     */
    public static void printFields(Class c1)
    {
        Field[] fields = c1.getDeclaredFields();
        AccessibleObject.setAccessible(fields, true);

//    log.addlog ("------------------print Fields ----------------");
        for (int i = 0; i < fields.length; i++)
        {
            Field f = fields[i];
//            Class type = f.getType();
//            String name = f.getName();
//      System.out.print(Modifier.toString(f.getModifiers()));
//      log.addlog(" | " + type.getName() + "| " + name + ";");
        }
    }

    /**
     * SchemaSet的转换
     * @param a SchemaSet
     * @param b SchemaSet
     * @return boolean
     */
    public boolean transFields(SchemaSet a, SchemaSet b)
    {
        if (a != null)
        {
            int n = b.size();
            try
            {
                Class c1 = a.getObj(1).getClass();
                a.clear();
//        log.addlog("====in"+n);
                for (int i = 1; i <= n; i++)
                {
//          log.addlog("---i:"+i);
                    Object c = c1.newInstance();
                    transFields((Schema) c, (Schema) b.getObj(i));
                    a.add((Schema) c);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            return false;
        }

        return true;
    }

    /**
     * 把对象b的值赋给对象a
     * @param a Schema
     * @param b Schema
     * @return Object
     */
    public Object transFields(Schema a, Schema b)
    {
        Class c1 = a.getClass();
        Class c2 = b.getClass();

        Field[] fieldsDest = c1.getDeclaredFields();
        Field[] fieldsOrg = c2.getDeclaredFields();

        AccessibleObject.setAccessible(fieldsDest, true);
        AccessibleObject.setAccessible(fieldsOrg, true);

        //log.addlog("----fieldDest.length:"+fieldsDest.length);
        for (int i = 0; i < fieldsDest.length; i++)
        {
            Field f = fieldsDest[i];
            Class type = f.getType();
            String name = f.getName();
            String typeName = type.getName();
            // log.addlog("[Time]::"+i+"[colname]:"+name+"[Typename]:"+typeName);
            if (name.equals("FIELDNUM") || name.equals("PK") ||
                name.equals("mErrors") || name.equals("fDate"))
            {
                continue;
            }
            for (int j = 0; j < fieldsOrg.length; j++)
            {
                //得到数据源的数据
                Field f1 = fieldsOrg[j];
                //                Class type1 = f1.getType();
                String name1 = f1.getName();
                String typeName1 = type.getName();
                //log.addlog("[times]:"+j+"[colname1]:"+name1+"[Typename1]:"+typeName1);
                //取出冗余变量

                if (name.equals("FIELDNUM") || name.equals("PK") ||
                    name.equals("mErrors") || name.equals("fDate"))
                {
                    continue;
                }
                //赋值转换
                if ((typeName.equals(typeName1)) && (name1.equals(name)))
                {

                    switch (transType(typeName))
                    {
                        case 3:
                            try
                            {
                                f.setDouble(a, f1.getDouble(b));
                            }
                            catch (Exception e)
                            {
                                e.printStackTrace();
                            }
                            break
                                    ;
                        case 5:
                            try
                            {
                                f.setInt(a, f1.getInt(b));
                            }
                            catch (Exception e)
                            {
                                e.printStackTrace();
                            }
                            break
                                    ;
                        case 93:
                            try
                            {
                                f.set(a, f1.get(b));
                            }
                            catch (Exception e)
                            {
                                e.printStackTrace();
                            }
                            break
                                    ;
                        default:
                            try
                            {
                                f.set(a, f1.get(b));
                                // log.addlog("------Default:"+f1.get(b));
                            }
                            catch (Exception e)
                            {
                                e.printStackTrace();
                            }
                            break
                                    ;
                    }
                }
            }
        }
        return a;
    }

    /**
     * 比较两个对象是否内容相同
     * @param a Schema
     * @param b Schema
     * @return boolean
     */
    public boolean compareFields(Schema a, Schema b)
    {
        boolean aFlag = true;

        mVResult.clear();

        Class c1 = a.getClass();
        Class c2 = b.getClass();

        Field[] fieldsDest = c1.getDeclaredFields();
        Field[] fieldsOrg = c2.getDeclaredFields();

        AccessibleObject.setAccessible(fieldsDest, true);
        AccessibleObject.setAccessible(fieldsOrg, true);

//    log.addlog ("------------------comp print Fields ----------------");
        for (int i = 0; i < fieldsDest.length; i++)
        {
            Field f = fieldsDest[i];
            Class type = f.getType();
            String name = f.getName();
            String typeName = type.getName();

            if (name.equals("FIELDNUM") || name.equals("PK") ||
                name.equals("mErrors") || name.equals("fDate"))
            {
                continue;
            }

            for (int j = 0; j < fieldsOrg.length; j++)
            {
                //得到数据源的数据
                Field f1 = fieldsOrg[j];
//                    Class type1 = f1.getType();
                String name1 = f1.getName();
                String typeName1 = type.getName();
                //取出冗余变量

                if (name.equals("FIELDNUM") || name.equals("PK") ||
                    name.equals("mErrors") || name.equals("fDate"))
                {
                    continue;
                }
                //赋值转换
                if ((typeName.equals(typeName1)) && (name1.equals(name)))
                {

                    switch (transType(typeName))
                    {
                        case 3:
                            try
                            {
                                if (f.getDouble(a) != f1.getDouble(b))
                                {
                                    String tStr = name + "^" +
                                                  f.getDouble(a) + "|" +
                                                  f1.getDouble(b);
                                    mVResult.addElement(tStr);
                                    aFlag = false;
                                }
                            }
                            catch (Exception e)
                            {
                                e.printStackTrace();
                            }
                            break
                                    ;
                        case 5:
                            try
                            {
                                if (f.getInt(a) != f1.getInt(b))
                                {
                                    String tStr = name + "^" + f.getInt(a) +
                                                  "|" + f1.getInt(b);
                                    mVResult.addElement(tStr);
                                    aFlag = false;
                                }
                            }
                            catch (Exception e)
                            {
                                e.printStackTrace();
                            }
                            break
                                    ;
                        case 93:
                            try
                            {
                                if (f.get(a) != f1.get(b))
                                {
                                    String tStr = name + "^" + f.get(a) +
                                                  "|" + f1.get(b);
                                    mVResult.addElement(tStr);
                                    aFlag = false;
                                }
                            }
                            catch (Exception e)
                            {
                                e.printStackTrace();
                            }
                            break
                                    ;
                        default:
                            try
                            {
                                if (f.get(a) != f1.get(b))
                                {
                                    String tStr = name + "^" + f.get(a) +
                                                  "|" + f1.get(b);
                                    mVResult.addElement(tStr);
                                    aFlag = false;
                                }
                            }
                            catch (Exception e)
                            {
                                e.printStackTrace();
                            }
                            break
                                    ;
                    }
                }
            }

        }
        return aFlag;
    }

    /**
     * 同步两个对象
     * @param a Schema
     * @param b Schema
     * @return Object
     */
    public Object synchronizeFields(Schema a, Schema b)
    {
//                boolean aFlag = true;

        mVResult.clear();

        Class c1 = a.getClass();
        Class c2 = b.getClass();

        Field[] fieldsDest = c1.getDeclaredFields();
        Field[] fieldsOrg = c2.getDeclaredFields();

        AccessibleObject.setAccessible(fieldsDest, true);
        AccessibleObject.setAccessible(fieldsOrg, true);

//    log.addlog ("------------------comp print Fields ----------------");
        for (int i = 0; i < fieldsDest.length; i++)
        {
            Field f = fieldsDest[i];
            Class type = f.getType();
            String name = f.getName();
            String typeName = type.getName();

            if (name.equals("FIELDNUM") || name.equals("PK") ||
                name.equals("mErrors") || name.equals("fDate"))
            {
                continue;
            }

            for (int j = 0; j < fieldsOrg.length; j++)
            {
                //得到数据源的数据
                Field f1 = fieldsOrg[j];
//                    Class type1 = f1.getType();
                String name1 = f1.getName();
                String typeName1 = type.getName();
                //取出冗余变量

                if (name.equals("FIELDNUM") || name.equals("PK") ||
                    name.equals("mErrors") || name.equals("fDate"))
                {
                    continue;
                }
                //赋值转换
                if ((typeName.equals(typeName1)) && (name1.equals(name)))
                {

                    switch (transType(typeName))
                    {
                        case 3:
                            try
                            {
                                if (f.getDouble(a) != f1.getDouble(b))
                                {
                                    f.setDouble(a, f1.getDouble(b));
                                }
                            }
                            catch (Exception e)
                            {
                                e.printStackTrace();
                            }
                            break
                                    ;
                        case 5:
                            try
                            {
                                if (f.getInt(a) != f1.getInt(b))
                                {
                                    f.setInt(a, f1.getInt(b));
                                }
                            }
                            catch (Exception e)
                            {
                                e.printStackTrace();
                            }
                            break
                                    ;
                        case 93:
                            try
                            {
                                if (f.get(a) != f1.get(b))
                                {
                                    f.set(a, f1.get(b));
                                }
                            }
                            catch (Exception e)
                            {
                                e.printStackTrace();
                            }
                            break
                                    ;
                        default:
                            try
                            {
                                if (f.get(a) != f1.get(b))
                                {
                                    f.set(a, f1.get(b));
                                }
                            }
                            catch (Exception e)
                            {
                                e.printStackTrace();
                            }
                            break
                                    ;
                    }
                }
            }
        }
//    log.addlog("---------------------------- compare end-----------------------");
        return a;
    }

    /**
     * 显示对象内容
     * @param a Object
     */
    public void printFields(Object a)
    {
        Class c1 = a.getClass();

        Field[] fieldsDest = c1.getDeclaredFields();

        AccessibleObject.setAccessible(fieldsDest, true);

//    log.addlog ("------------------trans print Fields ----------------");
        //log.addlog("----fieldDest.length:"+fieldsDest.length);
        for (int i = 0; i < fieldsDest.length; i++)
        {
            Field f = fieldsDest[i];
            Class type = f.getType();
            String name = f.getName();
            String typeName = type.getName();
            // log.addlog("[Time]::"+i+"[colname]:"+name+"[Typename]:"+typeName);
            if (name.equals("FIELDNUM") || name.equals("PK") ||
                name.equals("mErrors") || name.equals("fDate"))
            {
                continue;
            }
            //log.addlog("----fieldOrg.length:"+fieldsOrg.length);


            switch (transType(typeName))
            {
                case 3:

//          try
//          {
//            log.addlog("***************double::"+name+"---"+f.getDouble(a));
//          }
//          catch(Exception e)
//          {
//            e.printStackTrace();
//          }
                    break;
                case 5:

//          try
//          {
//            log.addlog("*************************Int::"+name+"---"+f.getInt(a));
//          }
//          catch(Exception e)
//          {
//            e.printStackTrace();
//          }
                    break;
                case 93:

//          try
//          {
//            log.addlog("*******************String::"+name+"---"+ f.get(a));
//          }
//          catch(Exception e)
//          {
//            e.printStackTrace();
//          }
                    break;
                default:

//          try
//        {
//          log.addlog("------Default:"+f.get(a));
//        }
//        catch(Exception e)
//        {
//          e.printStackTrace();
                    break;
//        }
            }

        }

//    log.addlog("-------- print end-------");

    }

    /**
     * 类型转换
     * @param type Object
     * @return int
     */
    private static int transType(Object type)
    {
        int typecode;
        typecode = 93;
        if (type.equals("java.lang.String"))
        {
            typecode = 93;
        }
        if (type.equals("double"))
        {
            typecode = 3;
        }
        if (type.equals("int"))
        {
            typecode = 5;
        }

        return typecode;
    }

    public boolean equals(Object otherobject)
    {
        if (this == otherobject)
        {
            return true;
        }

        if (otherobject == null)
        {
            return false;
        }

        if (getClass() != otherobject.getClass())
        {
            return false;
        }

        Reflections other = (Reflections) otherobject;

        return true;
    }

    public String toString()
    {
        Class ref = this.getClass();
//    log.addlog("ref:"+ref);
//    log.addlog("--------------------------");
        return getClass().getName();
    }

    public Vector getVResult()
    {
        return mVResult;
    }

    public static void main(String[] args)
    {
        /*
            try
            {
          String s = "manager";
          Object m = Class.forName(s).newInstance();
          log.addlog(m);
            }
            catch(Exception e)
            {
          e.printStackTrace();
            }
            log.addlog(Double[].class.getClass());
         */
//            String name;
        if (args.length > 0)
        {
//                name = args[0];
        }
        else
        {
            //name = JOptionPane.showInputDialog("class name (e.g.java.util.Date): " );
//                name = "java.util.Date";
        }
        try
        {
            /* base test
             log.addlog("-----------------------begin-----------------");
                  Object a=new LPPolSchema();
                  log.addlog("------a:"+a);
                  Class c2 = a.getClass();
                  log.addlog("-----c2:"+c2);
                  Class superc2 = c2.getSuperclass();
                  log.addlog("---superc2:"+superc2);
                  Class highc2 = superc2.getSuperclass();
                  log.addlog("---highc2:"+highc2);

                  Class c1 = Class.forName(name);
                  log.addlog("---c1"+c1);
                  Class superc1 = c1.getSuperclass();
                  log.addlog("---superc1:"+superc1);
                  System.out.print("Class : "+ name);
                  if (superc1 != null && superc1 != Object.class)
              System.out.print("extends " + superc1.getName());
                  System.out.print("\n{\n");
                  printConstructors(c1);
                  log.addlog();
                  printMethods(c1);
                  log.addlog();
                  printFields(c1);
                  log.addlog();
             */
            Reflections aReflections = new Reflections();
//            LPEdorMainSchema b = new LPEdorMainSchema();
//            LPEdorMainSchema c = new LPEdorMainSchema();
//            b.setEdorNo("tjjjtjjtjjtjj");
//     b.setPolNo("11111111111");
//     b.setEdorType("BQ");
//            c.setEdorNo("hsthsthts");
//     c.setPolNo("22222222222");
//     c.setEdorType("PG");

            /* SchemaSet transFields
                  LPPolSchema a = new LPPolSchema();
                  LPEdorMainSchema b = new LPEdorMainSchema();
                  LPEdorMainSet c = new LPEdorMainSet();
                  LPPolSet d = new LPPolSet();


                  c.clear();
                  b.setEdorNo("tjjjtjjtjjtjj");
                  b.setPolNo("11111111111");
                  b.setEdorType("BQ");
                  c.add(b);

                  b = new LPEdorMainSchema();
                  b.setEdorNo("hsthsthts");
                  b.setPolNo("22222222222");
                  b.setEdorType("PG");
                  c.add(b);

                  d.add(a);
                  aReflections.printFields(b);
                  if (!aReflections.transFields(d,c))
                  {
              log.addlog("d is null");
                  }
                  int n = d.size();
                  log.addlog("-----:"+n);
                  for (int i =1;i<=n;i++)
                  {
              a = d.get(i);
              log.addlog("-----a:"+a.getEdorNo());
              log.addlog("-----type:"+a.getEdorType());
              log.addlog("-----pol:"+a.getPolNo());
                  }
             */
//            aReflections.compareFields(b, c);
            Vector v = aReflections.getVResult();
//     for (int i=0;i<v.size();i++)
//     {
//       log.addlog((String)v.get(i));
//     }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
