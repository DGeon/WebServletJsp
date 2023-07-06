package kr.co.ovmkas.jsp.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import kr.co.ovmkas.jsp.domain.Member;


//중요한 파일 여러 곳에서 사용 할 것으로 예상됨
public class ParamSolver {
	
		/**
		 * 로그인 여부 메소드
		 * @param req
		 * @return
		 */
		public static boolean isLogin(HttpServletRequest req) {
			return req.getSession().getAttribute("member") != null;
		}
		


		/**
		 * 세션에 있는 member를 가져오고
		 * 글 작성자와 댓글 작성자를 비교하기 위한 메소드
		 * 
		 * @param req
		 * @param writer
		 * @return
		 */
		public static boolean isMine(HttpServletRequest req, String writer) {
			return isLogin(req) && ((Member)req.getSession().getAttribute("member")).getId().equals(writer);
		}
		
		
	
	/**
	 * 
	 *	대량의 getParamter 대신 사용
	 *  어느 Class나 대응 가능
	 * @param <T>
	 * @param req
	 * @param clazz
	 * @return
	 */
	public static <T> T getParams(HttpServletRequest req, Class<T> clazz) {
		T t=null;
		try {
			t = clazz.getDeclaredConstructor().newInstance();
			// 선언 필드에 대한 타입 및 이름 체크
			Field[] fields = clazz.getDeclaredFields();// 선언한 필드를 가져온다
			for (Field f : fields) {
//			System.out.println(f.getType() + " : " +  f.getName());
				String fieldName = f.getName();
				String setterName = "set"+fieldName.substring(0, 1).toUpperCase()+ fieldName.substring(1);
				Method[] methods = clazz.getDeclaredMethods();
				for (Method m : methods) {
					if (setterName.equals(m.getName())) {
						if(req.getParameter(fieldName)==null) {
							continue;
						}
						if (f.getType() == int.class || f.getType()==Integer.class) {
							m.invoke(t, Integer.parseInt(req.getParameter(fieldName)));
						}
						if(f.getType() == String.class) {
							m.invoke(t, req.getParameter(fieldName));
						}
						if(f.getType() == String[].class) {
							m.invoke(t, (Object)req.getParameterValues(fieldName));
						}
						if(f.getType() == Long.class || f.getType()==long.class) {
							m.invoke(t, Long.valueOf(req.getParameter(fieldName)));
						}
					}
				}
			}
//			System.out.println(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}

	public static void main(String[] args) {
//		getParams(null, Criteria.class);
//		System.out.println();
	}
}
