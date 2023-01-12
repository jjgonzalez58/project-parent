package com.stc.wms.security.admin;

import com.stc.wms.loginuser.model.LoginRequest;
import com.stc.wms.loginuser.model.LoginResponse;
import com.stc.wms.loginuser.model.dto.ValidateUserResponse;
import com.stc.wms.loginuser.service.LoginService;
import com.stc.wms.security.dto.UserDTO;
import com.stc.wms.security.model.SessionModel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zkoss.zk.ui.Session;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.UUID;

/**
 * User: job
 * Date: 1/12/22
 * Time: 18:03
 *
 * @author job
 */
@Service("securityWebUtils")
@Slf4j
@Getter
@Setter
public class SecurityWebUtils {
    private UserDTO user;
    private LoginService loginService;

    @Autowired
    public SecurityWebUtils(LoginService loginService){
        this.loginService = loginService;
    }
    private static final String hexArray = "0123456789ABCDEF98754321FEDCBA01";

    public boolean validateSession(Session session){
        if (session.getAttribute(SessionModel.BEAN_NAME) == null){
            log.error("Session no econtrada");
            return false;
        }
        return true;
//        SessionModel userSesion = (SessionModel) session.getAttribute(SessionModel.BEAN_NAME);
//        return userSesion.getSessionId().equals(sessionModel.getSessionId());
    }
    public void createUserSession(Session session,String user, String token){
       SessionModel  modelS = new SessionModel();
       modelS.setSessionId(token);
       modelS.setUserName(user);
       modelS.setFechaInicio(Calendar.getInstance());
       session.setAttribute(SessionModel.BEAN_NAME,modelS);
    }
    public String tokenSession(String user){
        UUID  uuid = generateType5UUID(hexArray,user);
        String sesionId = String.valueOf(uuid.getLeastSignificantBits()).replaceAll("-","");
        log.info("UUID: "+sesionId);
        return sesionId;
    }
    public static UUID generateType5UUID(String namespace, String name) {

        final byte[] nameSpaceBytes = bytesFromUUID(namespace);
        final byte[] nameBytes = name.getBytes(StandardCharsets.UTF_8);
        final byte[] result = joinBytes(nameSpaceBytes, nameBytes);

        return type5UUIDFromBytes(result);
    }
    private static byte[] bytesFromUUID(String uuidHexString) {
        final String normalizedUUIDHexString = uuidHexString.replace("-", "");

        assert normalizedUUIDHexString.length() == 32;

        final byte[] bytes = new byte[16];
        for (int i = 0; i < 16; i++) {
            final byte b = hexToByte(normalizedUUIDHexString.substring(i * 2, i * 2 + 2));
            bytes[i] = b;
        }
        return bytes;
    }
    public static byte hexToByte(String hexString) {
        final int firstDigit = Character.digit(hexString.charAt(0), 16);
        final int secondDigit = Character.digit(hexString.charAt(1), 16);
        return (byte) ((firstDigit << 4) + secondDigit);
    }
    public static byte[] joinBytes(byte[] byteArray1, byte[] byteArray2) {
        final int finalLength = byteArray1.length + byteArray2.length;
        final byte[] result = new byte[finalLength];

        System.arraycopy(byteArray1, 0, result, 0, byteArray1.length);
        System.arraycopy(byteArray2, 0, result, byteArray1.length, byteArray2.length);
        return result;
    }
    public static UUID type5UUIDFromBytes(byte[] name) {
        final MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException exception) {
            throw new InternalError("SHA-1 not supported", exception);
        }
        final byte[] bytes = Arrays.copyOfRange(md.digest(name), 0, 16);
        bytes[6] &= 0x0f; /* clear version        */
        bytes[6] |= 0x50; /* set to version 5     */
        bytes[8] &= 0x3f; /* clear variant        */
        bytes[8] |= 0x80; /* set to IETF variant  */
        return constructType5UUID(bytes);
    }
    private static UUID constructType5UUID(byte[] data) {
        long msb = 0;
        long lsb = 0;
        assert data.length == 16 : "data must be 16 bytes in length";

        for (int i = 0; i < 8; i++) {msb = (msb << 8) | (data[i] & 0xff);}

        for (int i = 8; i < 16; i++) {lsb = (lsb << 8) | (data[i] & 0xff);}
        return new UUID(msb, lsb);
    }
    public LoginResponse getLoginUser(LoginRequest request){
        LoginResponse  response = this.loginService.login(request);
        if (response.getCodError() == 0){
            log.info("Usuario ---- "+response.getLoginUser().getName());
            this.setUser(response.getLoginUser());
        }
        return response;
    }
    public ValidateUserResponse validateUser(LoginRequest request){
        return this.loginService.validateUser(request);
    }
    public String getUserName(){
        return user != null ? user.getName()+" "+user.getLastName() : "";
    }

}
