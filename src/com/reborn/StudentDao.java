package com.reborn;

import java.util.TreeMap;


/**
 * 学生对象操作接口类
 *
 */
public interface StudentDao {
    /**
     * 添加学生对象
     *
     * @param stuMap
     *            学生对象集合
     */
    public void addStu(TreeMap<String, Student> stuMap);
    /**
     * 更新学生对象
     *
     * @param stuMap
     *            新的学生对象集合
     */
    public void updateStu(TreeMap<String, Student> stuMap);
    /**
     * 查找所有学生对象
     *
     * @return 所有学生对象集合
     */
    public TreeMap<String, Student> findAll();
    /**
     * 删除所有学生对象
     */
    public void delAll();
    /**
     * 根据学生学号删除学生对象
     *
     * @param num
     */
    public boolean delByNum(String num);
    /**
     * 根据学生学号查询学生对象信息
     *
     * @param num
     * @return
     */
    public Student findByNum(String num);

    public void updateByNum(String num,Student student);
}