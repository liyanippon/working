package com.yifeng.web.controller.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ds.business.controller.BaseController;
import com.ds.business.dto.CodeDto;
import com.ds.business.pagination.PageData;
import com.ds.business.pagination.PageRequest;
import com.ds.fw.exception.BusinessException;
import com.ds.system.api.CodeApi;
import com.yifeng.identify.api.CategoryApi;
import com.yifeng.identify.api.ContractApi;
import com.yifeng.identify.api.NumberApi;
import com.yifeng.identify.dto.CategoryDto;
import com.yifeng.identify.dto.ContractDto;

@Controller
@RequestMapping(value = "/contract")
public class ContractController extends BaseController{
	
	@Autowired
	private ContractApi contractApi;
	
	@Autowired
    private CodeApi codeApi;
	
	@Autowired
	private NumberApi numberApi;
	
	@Autowired
	private CategoryApi categoryApi;
	
	/**
	 * 
	 * @return String
	 */
	@RequestMapping(value = "/showContractList.jhtml")
	public String showContractList() {
		return "/contract/contractList";
	}
	
	/**
	 * 查询数据库中所有合同
	 * 
	 * @param request
	 * @return pageData
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/loadContractPageData.jhtml")
	@ResponseBody
	public PageData<ContractDto> loadContractList(HttpServletRequest request) throws BusinessException {
		PageRequest pageRequest = this.getPageRequest(request);
		return contractApi.getContractListByPage(pageRequest);
	}
	
	/**
     * 根据id查询合同
     */
    @RequestMapping(value = "/getContract.jhtml")
    @ResponseBody
    public ContractDto getContract(HttpServletRequest request){
    	String id = request.getParameter("id");
    	ContractDto contractDto = contractApi.selectById(id);
    	return contractDto;
    }
    
    /**
     * 根据id删除合同
     * */
    @RequestMapping(value = "/deleteContract.jhtml")
    @ResponseBody
    public int deleteContract(HttpServletRequest request){
    	String id = request.getParameter("id");
    	return contractApi.delete(id);
    }
    
    /**
	 * 点击详情页面跳转
	 * */
	@RequestMapping(value = "/contractDetail.jhtml")
	public String contractDetail(HttpServletRequest request) {
		return "/contract/contractDetail";
	}
	
	/**
	 * 修改合同
	 * */
	@RequestMapping(value = "/updateContract.jhtml")
    @ResponseBody
    public boolean updateContract(HttpServletRequest request){
		ContractDto ContractDto = new ContractDto();
		ContractDto.setId(request.getParameter("id"));
		ContractDto.setFirstPeople(request.getParameter("firstPeople"));
		ContractDto.setSecondPeople(request.getParameter("secondPeople"));
		ContractDto.setRelevantPeople(request.getParameter("relevantPeople"));
		ContractDto.setCategory(request.getParameter("category"));
		ContractDto.setVarieties(request.getParameter("varieties"));
		ContractDto.setNumber(request.getParameter("number"));
		ContractDto.setPrice(request.getParameter("price"));
		ContractDto.setQuality(request.getParameter("quality"));
		ContractDto.setPayment(request.getParameter("payment"));
		ContractDto.setSettlement(request.getParameter("settlement"));
		ContractDto.setDelivery(request.getParameter("delivery"));
		return contractApi.update(ContractDto); 
	}
	
	
	/**
	 * 新建合同
	 * */
	@RequestMapping(value = "/createContract.jhtml")
    @ResponseBody
    public void createContract(HttpServletRequest request,ContractDto contractDto){
		contractDto.setFirstPeople(request.getParameter("firstPeople"));
		contractDto.setSecondPeople(request.getParameter("secondPeople"));
		contractDto.setRelevantPeople(request.getParameter("relevantPeople"));
		contractDto.setCategory(request.getParameter("category"));
		contractDto.setVarieties(request.getParameter("varieties"));
		contractDto.setNumber(request.getParameter("number"));
		contractDto.setPrice(request.getParameter("price"));
		contractDto.setQuality(request.getParameter("quality"));
		contractDto.setPayment(request.getParameter("payment"));
		contractDto.setSettlement(request.getParameter("settlement"));
		contractDto.setDelivery(request.getParameter("delivery"));
		contractDto.setCreateBy(this.getUserName(request));
		contractDto.setUpdateBy(this.getUserName(request));
		contractDto.setLsh(numberApi.getNumberByType("contract"));
		this.contractApi.insert(contractDto);
	}
	
	
	
	/**
	 * 下拉框 类别
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getCategory.jhtml")
	@ResponseBody
	public List<CategoryDto> getCategory() throws BusinessException {
		return categoryApi.getCategory();
}
	
	/**
	 * 下拉框 交付方式
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getDelivery.jhtml")
	@ResponseBody
	public List<CodeDto> getDelivery() throws BusinessException {
		return codeApi.getCodeListByType("013");
}
	
	/**
	 * 下拉框 付款方式
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getPayment.jhtml")
	@ResponseBody
	public List<CodeDto> getPayment() throws BusinessException {
		return codeApi.getCodeListByType("014");
}
	
	/**
	 * 下拉框 结算
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getSettlement.jhtml")
	@ResponseBody
	public List<CodeDto> getSettlement() throws BusinessException {
		return codeApi.getCodeListByType("015");
}
	
	

}
