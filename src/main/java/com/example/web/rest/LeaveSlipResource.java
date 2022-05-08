package com.example.web.rest;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import com.example.activiti5.dto.Action;
import com.example.activiti5.dto.ProcessResult;
import com.example.activiti5.service.ActivityService;
import com.example.domain.LeaveSlip;
import com.example.repository.LeaveSlipRepository;
import com.example.security.SecurityUtils;
import com.example.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.example.domain.LeaveSlip}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class LeaveSlipResource {

    private final Logger log = LoggerFactory.getLogger(LeaveSlipResource.class);

    private static final String ENTITY_NAME = "leaveSlip";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LeaveSlipRepository leaveSlipRepository;

    public LeaveSlipResource(LeaveSlipRepository leaveSlipRepository) {
        this.leaveSlipRepository = leaveSlipRepository;
    }

    @Autowired
    private ActivityService activityService;

    /**
     * {@code POST  /leave-slips} : Create a new leaveSlip.
     *
     * @param leaveSlip the leaveSlip to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new leaveSlip, or with status {@code 400 (Bad Request)} if the leaveSlip has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/leave-slips")
    public ResponseEntity<LeaveSlip> createLeaveSlip(@RequestBody LeaveSlip leaveSlip) throws URISyntaxException {
        log.debug("REST request to save LeaveSlip : {}", leaveSlip);
        if (leaveSlip.getId() != null) {
            throw new BadRequestAlertException("A new leaveSlip cannot already have an ID", ENTITY_NAME, "idexists");
        }

        //        约定新增状态为000
        leaveSlip.setWfstatus("000");
        String userid = SecurityUtils.getCurrentUserLogin().get();
        leaveSlip.setLeavePerson(userid);
        LeaveSlip result = leaveSlipRepository.save(leaveSlip);

        // 保存后才会带有id
        final Action action = new Action(Action.CREATE);
        //        临时写死, 实际应该从数据对象中获取, 工作流process identifier不能直接用纯数字，直接process_menuid来整
        String bpmnType = "process_5";
        Map<String, Object> map = BeanUtil.beanToMap(leaveSlip);
        final ProcessResult<Map> actionResult = activityService.doAction(bpmnType, action, Collections.singletonList(map), userid);

        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, leaveSlip.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /leave-slips/:id} : Updates an existing leaveSlip.
     *
     * @param id the id of the leaveSlip to save.
     * @param leaveSlip the leaveSlip to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated leaveSlip,
     * or with status {@code 400 (Bad Request)} if the leaveSlip is not valid,
     * or with status {@code 500 (Internal Server Error)} if the leaveSlip couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/leave-slips/{id}")
    public ResponseEntity<LeaveSlip> updateLeaveSlip(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody LeaveSlip leaveSlip
    ) throws URISyntaxException {
        log.debug("REST request to update LeaveSlip : {}, {}", id, leaveSlip);
        if (leaveSlip.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, leaveSlip.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!leaveSlipRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        LeaveSlip result = leaveSlipRepository.save(leaveSlip);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, leaveSlip.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /leave-slips/:id} : Partial updates given fields of an existing leaveSlip, field will ignore if it is null
     *
     * @param id the id of the leaveSlip to save.
     * @param leaveSlip the leaveSlip to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated leaveSlip,
     * or with status {@code 400 (Bad Request)} if the leaveSlip is not valid,
     * or with status {@code 404 (Not Found)} if the leaveSlip is not found,
     * or with status {@code 500 (Internal Server Error)} if the leaveSlip couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/leave-slips/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<LeaveSlip> partialUpdateLeaveSlip(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody LeaveSlip leaveSlip
    ) throws URISyntaxException {
        log.debug("REST request to partial update LeaveSlip partially : {}, {}", id, leaveSlip);
        if (leaveSlip.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, leaveSlip.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!leaveSlipRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<LeaveSlip> result = leaveSlipRepository
            .findById(leaveSlip.getId())
            .map(existingLeaveSlip -> {
                if (leaveSlip.getType() != null) {
                    existingLeaveSlip.setType(leaveSlip.getType());
                }
                if (leaveSlip.getStartTime() != null) {
                    existingLeaveSlip.setStartTime(leaveSlip.getStartTime());
                }
                if (leaveSlip.getEndTime() != null) {
                    existingLeaveSlip.setEndTime(leaveSlip.getEndTime());
                }
                if (leaveSlip.getReason() != null) {
                    existingLeaveSlip.setReason(leaveSlip.getReason());
                }
                if (leaveSlip.getFile() != null) {
                    existingLeaveSlip.setFile(leaveSlip.getFile());
                }
                if (leaveSlip.getSuperior() != null) {
                    existingLeaveSlip.setSuperior(leaveSlip.getSuperior());
                }

                return existingLeaveSlip;
            })
            .map(leaveSlipRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, leaveSlip.getId().toString())
        );
    }

    /**
     * {@code GET  /leave-slips} : get all the leaveSlips.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of leaveSlips in body.
     */
    @GetMapping("/leave-slips")
    public List<LeaveSlip> getAllLeaveSlips() {
        log.debug("REST request to get all LeaveSlips");
        return leaveSlipRepository.findAll();
    }

    /**
     * {@code GET  /leave-slips/:id} : get the "id" leaveSlip.
     *
     * @param id the id of the leaveSlip to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the leaveSlip, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/leave-slips/{id}")
    public ResponseEntity<LeaveSlip> getLeaveSlip(@PathVariable Long id) {
        log.debug("REST request to get LeaveSlip : {}", id);
        Optional<LeaveSlip> leaveSlip = leaveSlipRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(leaveSlip);
    }

    /**
     * {@code DELETE  /leave-slips/:id} : delete the "id" leaveSlip.
     *
     * @param id the id of the leaveSlip to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/leave-slips/{id}")
    public ResponseEntity<Void> deleteLeaveSlip(@PathVariable Long id) {
        log.debug("REST request to delete LeaveSlip : {}", id);
        leaveSlipRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    @PostMapping("/leave-slips/audit")
    public List<LeaveSlip> auditLeaveSlip(@RequestParam List<Long> idList) {
        log.debug("REST request to audit LeaveSlip : {}", idList);
        if (Objects.isNull(idList)) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        // 1. 根据id获取所有数据
        final List<LeaveSlip> auditList = leaveSlipRepository.findAllById(idList);

        //        2. 审核流程
        String userid = SecurityUtils.getCurrentUserLogin().get();
        final Action action = new Action(Action.AUDIT);
        String bpmnType = "process_5";
        final List<Map> collect = auditList.stream().map(leaveSlip -> BeanUtil.beanToMap(leaveSlip)).collect(Collectors.toList());
        final ProcessResult<List<Map>> actionResult = activityService.doAction(bpmnType, action, collect, userid);

        //        3. 更新要素信息
        final List<Map> result = actionResult.getResult();
        final Map<Object, List<Map>> groupByBusinesskey = result.stream().collect(Collectors.groupingBy(m -> m.get("businessKey")));

        //        4. 保存数据
        for (LeaveSlip leaveSlip : auditList) {
            final Long id = leaveSlip.getId();
            //           这里get必须用String, 不能用long类型，否则获取不到
            final List<Map> maps = groupByBusinesskey.get(String.valueOf(id));
            leaveSlip.setWfstatus(String.valueOf(maps.get(0).get("description")));
        }
        leaveSlipRepository.saveAll(auditList);
        return auditList;
    }
}
