package com.reborn;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.TreeMap;

/**
 * 学生对象操作实现类
 *
 */
public class StudentDaoImpl implements StudentDao {
    private static final String file = "student.dat";

    /**
     * 添加学生
     */
    @Override
    public void addStu(TreeMap<String, Student> stuMap) {
        /** 把原来的数据也加入去 */
        Iterator<Student> iterator = findAll().values().iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            stuMap.put(student.getNum(), student);
        }

        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(file);
            out = new ObjectOutputStream(fos);
            Iterator<Student> it = stuMap.values().iterator();
            while (it.hasNext()) {
                out.writeObject(it.next());
            }
            out.writeObject(null);
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 查找所有学生对象
     */
    @Override
    public TreeMap<String, Student> findAll() {
        FileInputStream fis = null;
        ObjectInputStream in = null;
        System.out.println("Hello");
        TreeMap<String, Student> stuMap = null;
        try {
            /**
             * 构造ObjectInputStream对象
             */
            fis = new FileInputStream(file);
            in = new ObjectInputStream(fis);
            Student student = null;
            stuMap = new TreeMap<String, Student>();
            while ((student = (Student) (in.readObject())) != null) {
                stuMap.put(student.getNum(), student);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Hello_File");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Hello_IO");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Hello_Class");
            e.printStackTrace();
        }
        return stuMap;
    }
    /**
     * 删除所有学生对象
     */
    @Override
    public void delAll() {
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(file);
            out = new ObjectOutputStream(fos);
            out.writeObject(null);
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 更新学生对象
     */
    @Override
    public void updateStu(TreeMap<String, Student> stuMap) {
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(file);
            out = new ObjectOutputStream(fos);
            Iterator<Student> it = stuMap.values().iterator();
            while (it.hasNext()) {
                out.writeObject(it.next());
            }
            out.writeObject(null);
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean delByNum(String num) {
        boolean flag = false;
        TreeMap<String, Student> stuMap = findAll();
        if (stuMap.remove(num) != null) {
            updateStu(stuMap);
            flag = true;
        }
        return flag;
    }

    @Override
    public Student findByNum(String num) {
        return findAll().get(num);
    }

    @Override
    public void updateByNum(String num, Student student) {
        TreeMap<String, Student> stuMap = findAll();
        stuMap.get(num).setId(student.getId());
        stuMap.get(num).setName(student.getName());
        stuMap.get(num).setNum(student.getNum());
        stuMap.get(num).setSex(student.getSex());
        updateStu(stuMap);
    }

}