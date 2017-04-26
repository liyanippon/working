package com.yifeng.web.controller.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ds.business.controller.BaseController;
import com.ds.common.utils.BeanUtils;
import com.ds.common.utils.StringUtils;
import com.ds.ump.resource.api.MenuApi;
import com.ds.ump.resource.dto.MenuDto;
import com.yifeng.web.vo.system.MenuVO;

/**
 * 菜单控制
 * 
 * @author wangyu
 *
 */
@Controller
@RequestMapping(value = "/system")
public class MenuController extends BaseController {
    
    @Autowired
    private MenuApi menuApi;
    
    
    @RequestMapping(value = "/getMenuByUser.jhtml")
    @ResponseBody
    public Set<MenuVO> getMenuList(HttpServletRequest request, String menuType) {
        List<MenuDto> menuDtoList = this.menuApi.getMenuList(menuType, this.getUserName(request));
        
        Set<MenuVO> menuSet = new TreeSet<MenuVO>();
        

        Map<String, Set<MenuVO>> tempSubMenuMap = new HashMap<String, Set<MenuVO>>();
        
        for (MenuDto each : menuDtoList) {
            MenuVO menu = this.createVO(each);
            if (StringUtils.isEmpty(menu.getParentId()) || "-1".equals(menu.getParentId())) {
                // 一级菜单
                menuSet.add(menu);
            } else {
                // 二级菜单
                Set<MenuVO> subSet = tempSubMenuMap.get(menu.getParentId());
                
                if (subSet == null) {
                    subSet = new TreeSet<MenuVO>();
                    subSet.add(menu);
                    tempSubMenuMap.put(menu.getParentId(), subSet);
                } else {
                    subSet.add(menu);
                }
                
            }
        }
        
        for(MenuVO each : menuSet) {
            each.setSubMenu(tempSubMenuMap.get(each.getId()));
        }
    
        return menuSet;
    }
    
    private MenuVO createVO(MenuDto dto) {
        MenuVO vo = new MenuVO();
        BeanUtils.copyProperties(dto, vo);
        return vo;
    }
    
   
}
