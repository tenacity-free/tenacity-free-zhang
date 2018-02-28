package com.tenacity.free.project.manager.service;

import com.tenacity.free.project.manager.po.ProjectManagerDocument;
import com.tenacity.free.project.manager.po.ReturnT;
import org.springframework.ui.Model;

/**
 * @author free.zhang
 * @project_name tenacity-free-zhang
 * @package_name com.tenacity.free.project.manager.service
 * @file_name ProjectManagerDocumentService.java
 * @description
 * @create 2018-02-28 10:39
 */
public interface ProjectManagerDocumentService {

    ReturnT<String> markStar(int id, int starLevel);

    ReturnT<String> delete(int id);

    String addPage(Model model, int productId);

    ReturnT<Integer> add(ProjectManagerDocument projectManagerDocument);

    String updatePage(Model model, int id);

    ReturnT<String> update(ProjectManagerDocument projectManagerDocument);

    String detailPage(Model model, int id);
}
