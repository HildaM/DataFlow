package com.quan.dataflow.dataflowsupport.dao;

import com.quan.dataflow.dataflowsupport.domain.SmsRecord;
import org.springframework.data.repository.CrudRepository;

/**
 * @ClassName: SmsRecordDao
 * @Description: 短信记录Dao
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2022/4/25 19:51
 */
public interface SmsRecordDao extends CrudRepository<SmsRecord, Long> {
}
