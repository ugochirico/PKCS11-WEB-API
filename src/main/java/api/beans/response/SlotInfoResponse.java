package api.beans.response;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SlotInfoResponse {
	
	private String manufacturerID;
	private String slotDescription;
	private long idToken;
	private boolean isHardwareSlot;
	private boolean isRemovableDevice; 
	private boolean isTokenPresent;
	
	public String getManufacturerID() {
		return manufacturerID;
	}
	public void setManufacturerID(String manufacturerID) {
		this.manufacturerID = manufacturerID;
	}
	public String getSlotDescription() {
		return slotDescription;
	}
	public void setSlotDescription(String slotDescription) {
		this.slotDescription = slotDescription;
	}
	public boolean isHardwareSlot() {
		return isHardwareSlot;
	}
	public void setHardwareSlot(boolean isHardwareSlot) {
		this.isHardwareSlot = isHardwareSlot;
	}
	public boolean isRemovableDevice() {
		return isRemovableDevice;
	}
	public void setRemovableDevice(boolean isRemovableDevice) {
		this.isRemovableDevice = isRemovableDevice;
	}
	public boolean isTokenPresent() {
		return isTokenPresent;
	}
	public void setTokenPresent(boolean isTokenPresent) {
		this.isTokenPresent = isTokenPresent;
	}
	public long getIdToken() {
		return idToken;
	}
	public void setIdToken(long idToken) {
		this.idToken = idToken;
	} 


}
