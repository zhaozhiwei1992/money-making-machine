package com.example.web.rest;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import com.example.domain.Authority;
import com.example.repository.AuthorityRepository;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing {@link Authority}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class RoleResource {

    private final Logger log = LoggerFactory.getLogger(RoleResource.class);

    private final AuthorityRepository authorityRepository;

    public RoleResource(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    /**
     * @data: 2022/5/6-上午10:11
     * @User: zhaozhiwei
     * @method: getMenusTree
     * @return: java.util.List<com.example.domain.Menu>
     * @Description: 需返回如下结果
     *       role_data: [
     *         {
     *           id: 0,
     *           label: '全部',
     *           children: [
     *             {
     *               id: 3,
     *               label: '二级 2-1',
     *             },
     *             {
     *               id: 2,
     *               label: '二级 2-2',
     *             },
     *           ],
     *         },
     *       ],
     */
    @GetMapping("/roles/tree")
    public List<Tree<Long>> getMenusTree() {
        log.debug("REST request to get Menus Tree");

        final List<String> roleList = authorityRepository.findAll().stream().map(Authority::getName).collect(Collectors.toList());

        //树形结构一些特殊配置
        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        // 自定义属性名 都要默认值的
        treeNodeConfig.setWeightKey("name");
        //        数据库设计如果和默认值一致，就不设置了
        //        treeNodeConfig.setIdKey("id");
        //        treeNodeConfig.setParentIdKey("parentId");
        //        treeNodeConfig.setChildrenKey("children");
        //        最大递归深度
        treeNodeConfig.setDeep(3);

        //转换器
        List<Tree<Long>> treeNodes = TreeUtil.build(
            roleList,
            0L,
            treeNodeConfig,
            (roleName, tree) -> {
                tree.setId(1L);
                tree.setParentId(0L);
                tree.setName(roleName);
                tree.putExtra("label", roleName);
            }
        );

        // children默认给空, 防止前端解析报错
        for (Tree<Long> treeNode : treeNodes) {
            if (Objects.isNull(treeNode.getChildren())) {
                treeNode.setChildren(Collections.emptyList());
            }
        }

        log.info("角色树构建: {}", treeNodes);
        return treeNodes;
    }
}
