package com.quan.dataflow.dataflowsupport.dao;

import com.quan.dataflow.dataflowsupport.domain.MessageTemplate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @ClassName: MessageTemplateDao
 * @Description: 消息模板Dao
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2022/4/24 18:39
 */
public interface MessageTemplateDao extends JpaRepository<MessageTemplate, Long> {

    /**
     * 查询 列表（分页)
     * @param deleted  0：未删除 1：删除
     * @param pageable 分页对象
     * @return
     */
    List<MessageTemplate> findAllByIsDeletedEquals(Integer deleted, Pageable pageable);


    /**
     * 统计未删除的条数
     * @param deleted
     * @return
     */
    Long countByIsDeletedEquals(Integer deleted);
}

