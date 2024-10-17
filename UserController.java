package com.example;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netflix.discovery.DiscoveryClient;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class UserController {
	
//Map<String,User> users = new HashMap<String,User>();
	
	@Autowired
	InvestorRepository repo;
	
	
	
	@RequestMapping(value="/users/register",method=RequestMethod.POST)
	@ResponseBody
	public String registerUser(@ModelAttribute("balance")int balance,@ModelAttribute("password")String password,@ModelAttribute("email")String email) {
		Investor u = new Investor(email,password,balance);
		repo.save(u);
		return "<html><body bgcolor='coral'>Registered Successfully"+"<a href='/index.html'> Home to login </a>"+"</body></html>";
		
	}
	
	@RequestMapping(value="/users/all",method=RequestMethod.GET)
	@ResponseBody
	public Iterable<Investor> getAllRegisteredUsers(){
		return repo.findAll();
	}
	
	@RequestMapping(value="/users/{email}",method=RequestMethod.GET)
	@ResponseBody
	public Investor getUser(@PathVariable("email")String email){
		return repo.findByEmail(email);
	}
	
	@Autowired
	private DiscoveryClient dc;
	
//	@Value("${pivotal.tradeservice.name}")
//	protected String ts;
//	
//	@RequestMapping(value="/users/login",method=RequestMethod.POST)
//	public String loginUser(@ModelAttribute("email")String email,@ModelAttribute("password")String password,
//			HttpServletRequest request,HttpServletResponse response) throws IOException {
//		
//		System.out.println(ts);
//		System.out.println("Entered the loginUser method in UserController");
//		Investor uu = repo.findByEmail(email);
//		request.getSession().setAttribute("user",uu);
//		
//		if(repo.findByEmail(email)!=null) {
//			if(repo.findByEmail(email).getPassword().equals(password)) {
//				
//				//Discovery client
//				List<ServiceInstance> instances = dc.getInstances(ts);
//				System.out.println(instances.size());
//				URI uri =instances.get(0).getUri();
//				System.out.println("User-Service.loginUser.URI======"+ uri);
//				String url = uri + "/Trade.html";
//				System.out.println("====================================");
//				System.out.println("User-Service.loginUser.URI======"+ uri);
//				
//				try {
//					response.sendRedirect(url);
//				}catch(Exception e){
//					System.out.println("Error in dispatching");
//				}
//				return "error";
//			}
//			else {
//				return "PasswordError"; //Logical view name
//			}
//		}
//		else {
//			return "Sorry";
//		}
//	}


}
