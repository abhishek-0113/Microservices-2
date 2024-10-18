package com.example;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class TradeController {
	@Autowired
	private TradeRepository tradeRepository;
	

	private Double getCompanyPrice(String ticker) {
		Map<String,Double> companies =Map.of(
				"WIPRO", 298.45,
				"INFY", 949.95,
				"TCS", 2713.70,
				"TECHM", 485.85
				);
				return companies.get(ticker);
	}
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private DiscoveryClient dc;
	
	@Value("${pivotal.userservice.name}")
	protected String userService;
	
	@RequestMapping(value="/trade/do",method=RequestMethod.POST)
	public String tradeDo(@ModelAttribute("ticker") String ticker,@ModelAttribute("qty") int qty,
			HttpServletRequest request) {
		
		Double price = getCompanyPrice(ticker);
		Trade t = new Trade(ticker,price,qty);
		double total = price*qty;
		t.setTotalCost(total);
//		t.setTicker(ticker);
//		t.setPrice(price);
//		t.setQty(qty);
//		
        tradeRepository.save(t);
        
		List<ServiceInstance> instances = dc.getInstances(userService);
		URI uri =instances.get(0).getUri();
		
		System.out.println("Trade-Service.tradedo().URI======"+ uri);
		String url = uri.toString()+"/users/all";
		System.out.println("====================================");
		System.out.println("Trade-Service.tradedo().URI======"+ uri);
		
		Map<String,Object> aa = new HashMap<String,Object>();
		
		ResponseEntity<String> result = restTemplate.getForEntity(url, String.class, aa);
		
		if(result.getStatusCode() == HttpStatus.OK) {
			return result.getBody();
		}else {
			return null;
		}
		
		
//		return "<html><body bgcolor='coral'>Traded Successfully "+ user.getUserid()+" Your balance now is: "
//			+user.getBalance()
//			+"<BR><a href='/index.html'>Exit</a><BR><a href='/Trade.html'>Trade Again</a><br>"
//			+"</body></html>";
		
	}
	
	@RequestMapping(value="/trade/all",method=RequestMethod.GET)
	public List<Trade> getAllTrades(){
		return tradeRepository.findAll();
	}
	
	@RequestMapping(value="/trade/{ticker}",method=RequestMethod.GET)
	public List<Trade> getUser(@PathVariable("ticker")String ticker){
		System.out.println("Ticker received:" + ticker);
		return tradeRepository.findByTicker(ticker);
	}

}
