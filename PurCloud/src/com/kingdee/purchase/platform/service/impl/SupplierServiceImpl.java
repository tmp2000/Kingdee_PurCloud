package com.kingdee.purchase.platform.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kingdee.purchase.openapi.model.SupplierInfo;
import com.kingdee.purchase.platform.dao.ISupplierDao;
import com.kingdee.purchase.platform.service.ISupplierService;

@Service
public class SupplierServiceImpl implements ISupplierService{
	
	@Autowired
	private ISupplierDao supplierDao;
	
	public SupplierInfo getSupplierInfoByDestId(String id) {
		return supplierDao.getSupplierInfoByDestId(id);
	}
	
	public int saveSupplier(SupplierInfo supplier) {
		if(getSupplierInfoByDestId(supplier.getDestId()) == null){
			return supplierDao.saveSupplier(supplier);
		}
		return 0;
	}
	
	public int saveSuppliers(List<SupplierInfo> suppliers) {
		List<SupplierInfo> tempSuppliers = new ArrayList<SupplierInfo>();
		for(SupplierInfo info:suppliers){
			if(getSupplierInfoByDestId(info.getDestId()) == null){
				tempSuppliers.add(info);
			}
		}
		return supplierDao.batchSaveSupplier(tempSuppliers);
	}
}
