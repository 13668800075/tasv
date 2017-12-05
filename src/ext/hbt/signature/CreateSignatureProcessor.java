package ext.hbt.signature;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import wt.fc.ObjectReference;
import wt.fc.Persistable;
import wt.fc.PersistenceHelper;
import wt.fc.ReferenceFactory;
import wt.util.WTException;
import wt.util.WTPropertyVetoException;

import com.ptc.core.components.forms.DefaultObjectFormProcessor;
import com.ptc.core.components.forms.FormProcessingStatus;
import com.ptc.core.components.forms.FormResult;
import com.ptc.netmarkets.util.beans.NmCommandBean;

import ext.hbt.reviewobjecttype.model.TypeItem;
import ext.hbt.signature.model.CoordinateItem;

public class CreateSignatureProcessor extends DefaultObjectFormProcessor {

	public CreateSignatureProcessor()
	{

	}

	public FormResult doOperation(NmCommandBean nmcommandbean, List list) throws WTException
	{
		FormResult formresult = new FormResult(FormProcessingStatus.SUCCESS);
		// 读取页面的相关信息
		HashMap textHt= nmcommandbean.getText();
		HashMap comboxHt = nmcommandbean.getComboBox();
		String[] targetTypeoid = (String[]) nmcommandbean.getRequest().getParameterMap().get("typeItemId");
		ReferenceFactory referencefactory = new ReferenceFactory();
		TypeItem typeitem = (TypeItem) referencefactory.getReference(targetTypeoid[0]).getObject();
		
		String attrName=(String) textHt.get("attrName");
		String attrType=(String) ((ArrayList)comboxHt.get("attrType")).get(0);
		String xLocation=(String) textHt.get("xLocation");
		String yLocation=(String) textHt.get("yLocation");
		String fontSize=(String) textHt.get("fontSize");
		String fontType=(String) ((ArrayList)comboxHt.get("fontType")).get(0);
		String contentType=(String) ((ArrayList)comboxHt.get("contentType")).get(0);
		String signRange=(String) textHt.get("signRange");
		String rotation=(String) textHt.get("rotation"); 
		String imageWidth=(String) textHt.get("imageWidth");
		if(imageWidth==null)
			imageWidth="";
		String imageHeight=(String) textHt.get("imageHeight");
		if(imageHeight==null)
			imageHeight="";
		CoordinateItem item=SignatureDataUtil.getCoordinateItem(typeitem, attrType, attrName);
        if(item!=null)
        	throw new WTException("存在重复的类型配置信息，请重新选择！");
		item=CoordinateItem.newCoordinateItem();
		try
		{
			item.setTargetTypeRef(ObjectReference.newObjectReference(typeitem));
			item.setAttrName(attrName);
			item.setAttrType(attrType);
			item.setXLocation(xLocation);
			item.setYLocation(yLocation);
			item.setFontSize(fontSize);
			item.setFontType(fontType);
			item.setContentType(contentType);
			item.setSignRange(signRange);
			item.setRotation(rotation);
			item.setImageWidth(imageWidth);
			item.setImageHeight(imageHeight);
		}
		catch (WTPropertyVetoException e)
		{
			throw new WTException(e);
		}
		PersistenceHelper.manager.save(item);
		return formresult;
	}
}
