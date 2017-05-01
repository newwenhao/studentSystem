package com.reborn;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.TreeMap;


public class StudentMain {
    static StudentDao dao;
    static BufferedReader br;
    public static void main(String[] args) {
        /** 启动时判断数据文件是否存在,如不存在则新建. */
        File file = new File("student.dat");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        dao = new StudentDaoImpl();

        /** 获得控制台输入流 */
        br = new BufferedReader(new InputStreamReader(System.in));

        /** 进行死循环 */
        while (true) {
            System.out.println(getLine());
            System.out.println("=[0]主菜单\t\t\t\t\t\t=");
            System.out.println("=[1]查找学生\t\t\t\t\t\t=");
            System.out.println("=[2]增加学生\t\t\t\t\t\t=");
            System.out.println("=[3]删除一个学生\t\t\t\t\t=");
            System.out.println("=[4]删除所有学生\t\t\t\t\t=");
            System.out.println("=[5]学生列表\t\t\t\t\t\t=");
            System.out.println("=[6]更新学生\t\t\t\t\t\t=");
            System.out.println("=[x]退出\t\t\t\t\t\t=");
            System.out.println(getLine());
            System.out.print("请选择操作:");

            /** 获得控制台输入的内容 */
            String input = getInput();
            if (input.equals("")) {
                input = "x";
            }
            switch (input.charAt(0)) {
                case '2' :
                    addStu();
                    break;
                case 'x' :
                    System.out.println("退出成功,欢迎再次使用!");
                    System.exit(0);
                    break;
                case '5' :
                    findAll();
                    break;
                case '4' :
                    delAll();
                    break;
                case '3' :
                    delByNum();
                    break;
                case '1' :
                    findByNum();
                    break;
                case '6' :
                    updateByNum();
                    break;
                default :
                    System.out.println("不支持的系统指令,请重新输入!");
                    break;
            }
        }
    }

    /**
     * 根据学生学号查询学生对象信息
     */
    public static void findByNum() {
        System.out.print("请输入要查询的学生学号:");
        while (true) {
            String input = getInput();
            if (dao.findByNum(input) != null) {
                System.out.println(dao.findByNum(input));
                return;
            } else if (input.equals("")) {
                return;
            } else {
                System.out.print("查询失败,不存在该学生,请重新输入要删除的学生学号:");
            }
        }
    }

    /**
     * 根据学生学号删除学生对象
     */
    public static void delByNum() {
        System.out.println(getLine());
        findAll();/** 显示所有学生信息方便进行删除操作 */
        System.out.println(getLine());
        System.out.println("如需取消留空直接按回车.");
        System.out.print("请输入要删除的学生学号:");
        while (true) {
            String input = getInput();
            if (dao.delByNum(input)) {
                System.out.println("删除成功!");
                findAll();
                return;
            } else if (input.equals("")) {
                return;
            } else {
                System.out.print("删除失败,不存在该学生,请重新输入要删除的学生学号:");
            }
        }
    }
    /**
     * 学生列表
     */
    public static void findAll() {
        TreeMap<String, Student> stuMap = dao.findAll();
        Iterator<Student> iterator = stuMap.values().iterator();
        System.out.println("hello");
        if (!iterator.hasNext()) {
            System.out.println("数据库为空");
        }
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    /**
     * 删除所有学生对象
     */
    public static void delAll() {
        while (true) {
            System.out.print("是否要删除所有学生信息,确定请输入YES,否请输入NO：");
            String input = getInput();
            if (input.equals("YES") || input.equals("yes")) {
                dao.delAll();
                System.out.println("清空成功");
                return;
            } else if (input.equals("NO") || input.equals("no")) {
                return;
            } else {
                System.out.println("输入有误,请重新输入!");
            }
        }
    }

    /**
     * 添加学生
     */
    public static void addStu() {
        while (true) {
            System.out.print("请输入要添加学生的个数:");
            String ch = null;
            try {
                ch = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (ch.equals("")) {
                System.out.println("输入有误,请重新输入!");
            } else {
                int size = Integer.parseInt(ch);
                TreeMap<String, Student> stuMap = new TreeMap<String, Student>();
                for (int i = 0; i < size; i++) {
                    Student student = new Student();
                    try {
                        System.out.print("请输入第 " + (i + 1) + " 个学生的姓名:");
                        student.setName(br.readLine());
                        System.out.print("请输入第 " + (i + 1) + " 个学生的学号:");
                        student.setNum(br.readLine());
                        System.out.print("请输入第 " + (i + 1)
                                + " 个学生的性别(男输:1;女输:0):");
                        student.setSex(Integer.parseInt(br.readLine()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    stuMap.put(student.getNum(), student);
                }
                dao.addStu(stuMap);
                findAll();
                return;
            }
        }
    }

    public static void updateByNum() {
        findAll();
        System.out.print("请输入要修改的学生学号:");
        while (true) {
            String input = getInput();
            String num = input;
            Student student = dao.findByNum(num);
            if (student != null) {
                System.out.println(student);
                System.out.println("如不修改直接留空按回车!");
                System.out.print("请输入学生的新姓名:");
                input = getInput();
                if (!input.equals(""))
                    student.setName(input);
                System.out.print("请输入学生的新学号:");
                input = getInput();
                if (!input.equals(""))
                    student.setNum(input);
                System.out.print("请输入学生的新版性别(男输:1;女输:0):");
                input = getInput();
                if (!input.equals(""))
                    student.setSex(Integer.parseInt(input));
                dao.updateByNum(num, student);
                System.out.println("更新成功,更新后的数据:");
                System.out.println(dao.findByNum(student.getNum()));
                return;
            } else if (input.equals("")) {
                return;
            } else {
                System.out.print("查询失败,不存在该学生,请重新输入要修改的学生学号:");
            }
        }
    }

    public static void addTest() {
        TreeMap<String, Student> stuMap = new TreeMap<String, Student>();
        for (int i = 0; i < 10000; i++) {
            Student student = new Student(i, "A" + i, "NUM" + i, 0);
            stuMap.put(student.getNum(), student);
        }
        dao.addStu(stuMap);
        return;
    }

    /**
     * 获得===========分割符
     *
     * @return
     */
    public static String getLine() {
        return "=========================================================";
    }

    /**
     * 获得控制台输入
     *
     * @return
     */
    public static String getInput() {
        String ch = "";
        try {
            ch = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ch;
    }
}