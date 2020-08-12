package com.springbook.board.common;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.web.multipart.MultipartFile;



public class MyUtil {
	
	public static String gensalt() {
		return BCrypt.gensalt();
	}
	public static String hashPassword(String pw,String salt) {
		return BCrypt.hashpw(pw, salt);
	}
	/*
	 * public static UserVO getLoginUser(HttpServletRequest request) { HttpSession
	 * hs = request.getSession(); return (UserVO)hs.getAttribute("loginUser"); }
	 */
	
	//len:길이 0~9사이의 숫자
	public static String makeRandomNumber(int len) {
		 //난수가 저장될 변수
		String result = "";
		//0~9까지 난수 생성
		for(int i =0; i<len;i++) {
			result += Integer.toString((int)(Math.random()*10));
		}
		System.out.println("result:"+result);
		return result;
	}
		// 리턴값: 저장된 파일명
		//   "/resources/user/??"
		public static String saveFile(String path, MultipartFile file) {
			String fileNm = null;
			//파일명의 중복을 없애기 위해서
			UUID uuid = UUID.randomUUID();
			
			
			// 확장자
			String ext = FilenameUtils.getExtension(file.getOriginalFilename());
			System.out.println("ext : " + ext);
			
			//파일명(업로드한 파일명을 쓰지 않느다.)
			fileNm = String.format("%s.%s", uuid, ext);
			//파일 경로와 위치값
			String saveFileNm = String.format("%s/%s", path, fileNm);
			
			System.out.println("saveFileNm : " + saveFileNm);
			File saveFile = new File(saveFileNm);
			//폴더가 없다면 폴더를 만들어라
			saveFile.mkdirs();
			
			try {
				//파일을 saveFile경로로 저장한다.
				file.transferTo(saveFile); // 업로드 파일에 saveFile로 위치로 저장했다.
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
			//파일명을 리턴한다.
			return fileNm;
		}
		//이미지 삭제
		public static boolean deleteFile(String filePath) {
			boolean result = false;
			File file = new File(filePath);
			
			if(file.exists()) {
				result =file.delete();
			}
			return result;
		}
}
