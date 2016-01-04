package api.webservice.implementation;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import api.beans.request.ChangePasswordTokenBeanRequest;
import api.beans.request.InitTokenBeanRequest;
import api.beans.request.InitUserPasswordTokenBeanRequest;
import api.beans.response.TokenInfoResponse;
import api.error.entity.ErrorEntity;
import iaik.pkcs.pkcs11.Module;
import iaik.pkcs.pkcs11.Session;
import iaik.pkcs.pkcs11.Slot;
import iaik.pkcs.pkcs11.Token;
import iaik.pkcs.pkcs11.Token.SessionReadWriteBehavior;
import iaik.pkcs.pkcs11.Token.SessionType;
import iaik.pkcs.pkcs11.TokenException;
import iaik.pkcs.pkcs11.TokenInfo;

public class TokenWebServiceImplementation {

	public TokenInfoResponse tokenInfos(HttpServletRequest req, int idToken, List<String> select) {

		TokenInfoResponse ti = new TokenInfoResponse();
		Token t = null;
		Module m = (Module) req.getSession().getAttribute("module");
		if (m == null)
			throw new WebApplicationException(Response.status(Status.UNAUTHORIZED)
					.entity(new ErrorEntity("Module is not initialized")).build());

		try {
			
			Slot[] slots;
			slots = m.getSlotList(Module.SlotRequirement.ALL_SLOTS);
			if (idToken > slots.length)
				throw new WebApplicationException(Response.status(Status.BAD_REQUEST)
						.entity(new ErrorEntity("You're trying to use an out of range ID for the token")).build());
			if (select.size() == 0)
				throw new WebApplicationException(Response.status(Status.BAD_REQUEST)
						.entity(new ErrorEntity("You didn't specified any select")).build());
			t = slots[idToken].getToken();
			if (select.contains("*")) {
				select.clear();
				select.add("minorFirmwareVersion");
				select.add("majorFirmwareVersion");
				select.add("freePrivateMemory");
				select.add("freePublicMemory");
				select.add("minorHardwareVersion");
				select.add("majorHardwareVersion");
				select.add("label");
				select.add("manufacturerID");
				select.add("maxPinLen");
				select.add("minPinLen");
				select.add("serialNumber");
				select.add("totalPrivateMemory");
				select.add("totalPublicMemory");
				select.add("isDualCryptoOperations");
				select.add("isLoginRequired");
				select.add("isProtectedAuthenticationPath");
				select.add("isRestoreKeyNotNeeded");
				select.add("isRNG");
				select.add("isSecondaryAuthentication");
				select.add("isSoPinCountLow");
				select.add("isSoPinFinalTry");
				select.add("isSoPinLocked");
				select.add("isSoPinToBeChanged");
				select.add("isTokenInitialized");
				select.add("isUserPinCountLow");
				select.add("isUserPinFinalTry");
				select.add("isUserPinInitialized");
				select.add("isUserPinLocked");
				select.add("isUserPinToBeChanged");
				select.add("isWriteProtected");
			}
			try {
				if (select.contains("minorFirmwareVersion"))
					ti.setMinorFirmwareVersion(t.getTokenInfo().getFirmwareVersion().getMinor());
				if (select.contains("majorFirmwareVersion"))
					ti.setMajorFirmwareVersion(t.getTokenInfo().getFirmwareVersion().getMajor());
				if (select.contains("freePrivateMemory"))
					ti.setFreePrivateMemory(t.getTokenInfo().getFreePrivateMemory());
				if (select.contains("freePublicMemory"))
					ti.setFreePublicMemory(t.getTokenInfo().getFreePublicMemory());
				if (select.contains("minorHardwareVersion"))
					ti.setMinorHardwareVersion(t.getTokenInfo().getHardwareVersion().getMinor());
				if (select.contains("majorHardwareVersion"))
					ti.setMajorHardwareVersion(t.getTokenInfo().getHardwareVersion().getMajor());
				if (select.contains("label"))
					ti.setLabel(t.getTokenInfo().getLabel());
				if (select.contains("manufacturerID"))
					ti.setManufacturerID(t.getTokenInfo().getManufacturerID());
				if (select.contains("maxPinLen"))
					ti.setMaxPinLen(t.getTokenInfo().getMaxPinLen());
				if (select.contains("minPinLen"))
					ti.setMinPinLen(t.getTokenInfo().getMinPinLen());
				if (select.contains("serialNumber"))
					ti.setSerialNumber(t.getTokenInfo().getSerialNumber());
				if (select.contains("totalPrivateMemory"))
					ti.setTotalPrivateMemory(t.getTokenInfo().getTotalPrivateMemory());
				if (select.contains("totalPublicMemory"))
					ti.setTotalPublicMemory(t.getTokenInfo().getTotalPublicMemory());
				if (select.contains("isDualCryptoOperations"))
					ti.setDualCryptoOperations(t.getTokenInfo().isDualCryptoOperations());
				if (select.contains("isLoginRequired"))
					ti.setLoginRequired(t.getTokenInfo().isLoginRequired());
				if (select.contains("isProtectedAuthenticationPath"))
					ti.setProtectedAuthenticationPath(t.getTokenInfo().isProtectedAuthenticationPath());
				if (select.contains("isRestoreKeyNotNeeded"))
					ti.setRestoreKeyNotNeeded(t.getTokenInfo().isRestoreKeyNotNeeded());
				if (select.contains("isRNG"))
					ti.setRNG(t.getTokenInfo().isRNG());
				if (select.contains("isSecondaryAuthentication"))
					ti.setSecondaryAuthentication(t.getTokenInfo().isSecondaryAuthentication());
				if (select.contains("isSoPinCountLow"))
					ti.setSoPinCountLow(t.getTokenInfo().isSoPinCountLow());
				if (select.contains("isSoPinFinalTry"))
					ti.setSoPinFinalTry(t.getTokenInfo().isSoPinFinalTry());
				if (select.contains("isSoPinLocked"))
					ti.setSoPinLocked(t.getTokenInfo().isSoPinLocked());
				if (select.contains("isSoPinToBeChanged"))
					ti.setSoPinToBeChanged(t.getTokenInfo().isSoPinToBeChanged());
				if (select.contains("isTokenInitialized"))
					ti.setTokenInitialized(t.getTokenInfo().isTokenInitialized());
				if (select.contains("isUserPinCountLow"))
					ti.setUserPinCountLow(t.getTokenInfo().isUserPinCountLow());
				if (select.contains("isUserPinFinalTry"))
					ti.setUserPinFinalTry(t.getTokenInfo().isUserPinFinalTry());
				if (select.contains("isUserPinInitialized"))
					ti.setUserPinInitialized(t.getTokenInfo().isUserPinInitialized());
				if (select.contains("isUserPinLocked"))
					ti.setUserPinLocked(t.getTokenInfo().isUserPinLocked());
				if (select.contains("isUserPinToBeChanged"))
					ti.setUserPinToBeChanged(t.getTokenInfo().isUserPinToBeChanged());
				if (select.contains("isWriteProtected"))
					ti.setWriteProtected(t.getTokenInfo().isWriteProtected());
				return ti;
			} catch (TokenException e) {
				throw new WebApplicationException(Response.status(Status.INTERNAL_SERVER_ERROR)
						.entity(new ErrorEntity("Ooops- Error while filling the bean")).build());
			}
		} catch (TokenException e) {
			throw new WebApplicationException(Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity(new ErrorEntity("Ooops- Problem while retriving the slots")).build());
		}
	}


	public Response init(HttpServletRequest req, InitTokenBeanRequest r, int idToken) {
		Module m = (Module) req.getSession().getAttribute("module");
		if (m == null)
			throw new WebApplicationException(Response.status(Status.UNAUTHORIZED)
					.entity(new ErrorEntity("Module is not initialized")).build());
		try {
			Slot[] slots;
			slots = m.getSlotList(Module.SlotRequirement.ALL_SLOTS);
			if (idToken > slots.length)
				throw new WebApplicationException(Response.status(Status.BAD_REQUEST)
						.entity(new ErrorEntity("You're trying to use an out of range ID for the token")).build());
			Token t = slots[idToken].getToken();
			if (t.getTokenInfo().isTokenInitialized())
				throw new WebApplicationException(Response.status(Status.UNAUTHORIZED)
						.entity(new ErrorEntity("Your token is already initialized")).build());
			t.initToken(r.getPinSO().toCharArray(), r.getLabel());
		} catch (TokenException e) {
			throw new WebApplicationException(Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity(new ErrorEntity("Ooops- Problem while retriving the slots")).build());
		}
		
		return Response.status(Status.NO_CONTENT).build();

	}
	

	public Response reset(HttpServletRequest req, InitTokenBeanRequest r, int idToken) {
		Module m = (Module) req.getSession().getAttribute("module");
		if (m == null)
			throw new WebApplicationException(Response.status(Status.UNAUTHORIZED)
					.entity(new ErrorEntity("Module is not initialized")).build());
		try {
			Slot[] slots;
			slots = m.getSlotList(Module.SlotRequirement.ALL_SLOTS);
			if (idToken > slots.length)
				throw new WebApplicationException(Response.status(Status.BAD_REQUEST)
						.entity(new ErrorEntity("You're trying to use an out of range ID for the token")).build());
			Token t = slots[idToken].getToken();
			if (!t.getTokenInfo().isTokenInitialized())
				throw new WebApplicationException(Response.status(Status.UNAUTHORIZED)
						.entity(new ErrorEntity("Your token is not initialized")).build());
			TokenInfo tokenInfo = t.getTokenInfo();
			try{
				Session session = t.openSession(SessionType.SERIAL_SESSION, SessionReadWriteBehavior.RW_SESSION, null, null);
				if (tokenInfo.isLoginRequired()) {
				     // check, if the token has own means to authenticate the user; e.g. a PIN-pad on the reader
				     if (tokenInfo.isProtectedAuthenticationPath()) {
				       System.out.println("Please enter the user PIN at the PIN-pad of your reader.");
				       session.login(Session.UserType.SO, null); // the token prompts the PIN by other means; e.g. PIN-pad
				       session.closeSession();
				     } else {
				       session.login(Session.UserType.SO, r.getPinSO().toCharArray());
				       session.closeSession();
				     }
				   }
			} catch(TokenException e){
				throw new WebApplicationException(Response.status(Status.FORBIDDEN)
						.entity(new ErrorEntity("Wrong SO PIN")).build());
			}
			
			t.initToken(r.getPinSO().toCharArray(), r.getLabel());
		} catch (TokenException ee) {
			throw new WebApplicationException(Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity(new ErrorEntity("Ooops- Problem while retriving the slots")).build());
		}
		
		return Response.status(Status.NO_CONTENT).build();

	}


	@SuppressWarnings("unchecked")
	public Response changePassword(HttpServletRequest req, ChangePasswordTokenBeanRequest r, int idToken) {
		Session s;
		Token t;
		if(req.getSession().getAttribute("session") != null && ((Map<Integer,Session>) req.getSession().getAttribute("session")).get(Integer.valueOf(idToken)) != null){
			s = ((Map<Integer,Session>) req.getSession().getAttribute("session")).get(Integer.valueOf(idToken));
			t = s.getToken();
		}
		else{
			Module m = (Module) req.getSession().getAttribute("module");
			if (m == null)
				throw new WebApplicationException(Response.status(Status.UNAUTHORIZED)
						.entity(new ErrorEntity("Module is not initialized")).build());
			Slot[] slots;
			try {
				slots = m.getSlotList(Module.SlotRequirement.ALL_SLOTS);
				if (idToken > slots.length)
					throw new WebApplicationException(Response.status(Status.BAD_REQUEST)
							.entity(new ErrorEntity("You're trying to use an out of range ID for the token")).build());
				t = slots[idToken].getToken();
				s = t.openSession(SessionType.SERIAL_SESSION, SessionReadWriteBehavior.RW_SESSION, null, null);
			} catch (TokenException e) {
				throw new WebApplicationException(Response.status(Status.INTERNAL_SERVER_ERROR)
						.entity(new ErrorEntity("Ooops- Problem while retriving the slots")).build());
			}
			/*try {
				if (t.getTokenInfo().isLoginRequired()) {
				     // check, if the token has own means to authenticate the user; e.g. a PIN-pad on the reader
				     if (t.getTokenInfo().isProtectedAuthenticationPath()) {
				       System.out.println("Please enter the user PIN at the PIN-pad of your reader.");
				       s.login(Session.UserType.SO, null); // the token prompts the PIN by other means; e.g. PIN-pad;
				     } else {
				       s.login(Session.UserType.SO, r.getOldPin().toCharArray());
				     }
				   }
				if (t.getTokenInfo().isProtectedAuthenticationPath()){
					
				}
				else{
					s.setPIN(r.getOldPin().toCharArray(), r.getNewPin().toCharArray());
				}
			} catch (TokenException e) {
				throw new WebApplicationException(Response.status(Status.BAD_REQUEST)
						.entity(new ErrorEntity("Ooops- Problem while Setting the new PIN")).build());
			}*/
		}
		return null;
	}


	public Response initUserPin(HttpServletRequest req, InitUserPasswordTokenBeanRequest r, int idToken) {
		// TODO Auto-generated method stub
		return null;
	}
}

