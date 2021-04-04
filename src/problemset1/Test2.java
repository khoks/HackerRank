package problemset1;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

class BasicDataSource implements DataSource {

	String dbURL = "jdbc:postgresql://host/database";
	
	public BasicDataSource() {
		try {
			Class.forName("org.postgresql.Driver");
			DriverManager.registerDriver(new org.postgresql.Driver());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		char c = 'a';
		c = (char)((int)c + 2);
		
		

	    
	    Character c1 = 'a';
	    Character c2 = 'a';
	    System.out.println(c1 == c2);
	}
	
	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Connection getConnection() throws SQLException {
		Connection conn = DriverManager.getConnection(dbURL, "postgres", "password");
		return conn;
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		Connection conn = DriverManager.getConnection(dbURL, username, password);
		return conn;
	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	String serverName;
	String databaseName;
	String description;
	
	public void setServerName(String serverName) {
		this.serverName = serverName;
		this.dbURL = this.dbURL.replace("host", this.serverName);
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
		this.dbURL = this.dbURL.replace("database", this.databaseName);
	}

	public void setDescription(String description) {
		this.description = description;
		
	}
	
}

public class Test2 {

	public static void main(String[] args) {
		LinkedList<Integer> l1 = new LinkedList<Integer>();
		LinkedList<Integer> l2 = new LinkedList<Integer>();
		
		l1.push(1);l1.push(3);l1.push(5);l1.push(7);
		l2.push(2);l2.push(4);l2.push(6);l2.push(8);
		
		LinkedList<Integer> l3 = new LinkedList<Integer>();
		
		if(l1.getFirst() > l2.getFirst()) {
			l3.add(l3.pop());
		}
		while(l1.size() > 0 || l2.size() > 0) {
			if(!l1.isEmpty() && !l2.isEmpty()) {
				if(l1.getFirst() > l2.getFirst()) {
					l3.add(l1.pop());
				} else {
					l3.add(l2.pop());
				}
			} else {
				if(!l1.isEmpty()) {
					l3.add(l1.pop());
				} else if(!l2.isEmpty()) {
					l3.add(l2.pop());
				}
				else {
					break;
				}
			}
		}
		
		System.out.println(l3.toString());
	}
	
	public static void mainadasd(String[] args) { 
		
		BasicDataSource ds = new BasicDataSource();
		ds.setServerName("localhost");
		ds.setDatabaseName("postgres");
		ds.setDescription("Customer accounts database for billing");
		Context ctx = null;
		try {
			ctx = new InitialContext();
			ctx.bind("jdbc/billingDB", ds);
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		
		
		//"jdbc:postgresql:ProductDB&user=root&password=secret"
		try {
			
			
			
			//Class.forName("org.postgresql.Driver");
			
			//DriverManager.registerDriver(new org.postgresql.Driver());
			
			//String dbURL = "jdbc:postgresql:postgres";
			
			//Connection conn = DriverManager.getConnection(dbURL, "postgres", "password");
			
			Connection conn  = ds.getConnection();
			
			Statement st = conn.createStatement();
			
			st.execute("select * from information_schema.tables;");
			
			ResultSet rs = st.getResultSet();
			
			while(!rs.isClosed()) {
				if(rs.isBeforeFirst()) {
					rs.next();
				}
				
				
				IntStream.rangeClosed(0, 10).forEach(i -> {
					try {
						System.out.print(" Column : "+ i+ " | Value : " + rs.getString(i));
					} catch (SQLException e) {e.printStackTrace();}
				});
				
				System.out.println();
				
				
				if(!rs.isLast()) {
					rs.next();
				}
			}
			
			System.out.println();
			
			st.close();
			
			conn.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	public static void mainasdasd(String[] args) { 
		String[] strArr = new String[] {"as","xcv","qswae"};
		Map<Integer, String> strMap = Arrays.asList(strArr).stream()
				.collect(Collectors.toMap(String::length, String::toString, (o1, o2) -> o2));
		
		Map<Integer, String> strMap2 = strMap.entrySet().stream()
				.sorted(Map.Entry.comparingByKey())
					.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (o1, o2) -> o2, LinkedHashMap::new)); 
		
		Map<Integer, String> strMap3 = strMap.entrySet().stream()
				.sorted(Map.Entry.comparingByValue())
					.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (o1, o2) -> o2, LinkedHashMap::new));
		
		Map<Integer, String> strMap4 = strMap.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
					.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (o1, o2) -> o2, LinkedHashMap::new));
		
		Map<Integer, String> strMap5  = new LinkedHashMap<Integer, String>();
		
		strMap.entrySet().stream().sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
			.forEachOrdered( e -> strMap5.put(e.getKey(), e.getValue()) );
		
		System.getenv().entrySet().stream().forEach(e -> System.out.println("key - " + e.getKey() + " Value - " + e.getValue()));
		
		ArrayList<String> asda = new ArrayList<String>();
		asda.sort(new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				return 1;
			}
		});
		
		Stream<Integer> stream1 = Stream.of(1,3,5);
		Stream<Integer> stream2 = Stream.of(1,3,5);
		Stream<Integer> stream3 = Stream.of(1,3,5);
		Stream<Integer> stream4 = Stream.of(1,3,5);
		
		Stream<Integer> resultingStream = Stream.of(
			      stream1, stream2, stream3, stream4)
			      .flatMap(i -> i);
		
		System.out.println(strMap);
		System.out.println(strMap2);
		System.out.println(strMap3);
		System.out.println(strMap4);
		System.out.println(strMap5);
		Date d = new Date();
		System.out.println(d);
		System.out.println(d.toGMTString());
		System.out.println(d.toLocaleString());
		System.out.println(d.toString());
		System.out.println(d.getTime());
		System.out.println(d.toInstant());
		System.out.println(d.toInstant());
		
		DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss") ;
		System.out.println(sdf.format(d));
		
		Calendar c = Calendar.getInstance();
		System.out.println(c);
		System.out.println(c.getTime());
		
		//sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss") ;
		//System.out.println(sdf.format(c));
		
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	    Calendar cal = Calendar.getInstance();
	    //System.out.println(dateFormat.format(cal)); //2016/11/16 12:08:43
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy yyyy yyyy yy h m s hh mm ss d M dd MM ");
		
		
		System.out.println(new Date());
		System.out.println(Calendar.getInstance());
		System.out.println(LocalDateTime.now());
		System.out.println(LocalDate.now());
		System.out.println(dtf.format(LocalDateTime.now()));
		
		System.out.println(System.currentTimeMillis());
		Timestamp t1 = new Timestamp(System.currentTimeMillis());
		System.out.println(t1);
		
		
		String strDate = "Sunday 7 Feb 2017 AM Z".replaceAll("Z$", "+0000");
		SimpleDateFormat sdf2 = new SimpleDateFormat("EEEEE d MMM yyyy a Z");
		try {
			System.out.println(sdf2.format(sdf2.parseObject(strDate)));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(TimeZone.getDefault().getID());
	}
	
	public static void main1(String[] args) {
		
		ArrayList<Some> someArr = new ArrayList<Some>() ;
		IntStream.rangeClosed(0,  9).forEach(i -> someArr.add(new Some(i)));
		someArr.stream().collect(Collectors.groupingBy(Some::getI));
		
		IntStream.rangeClosed(0, 10).forEach(System.out::println);
		
		Stream.iterate(0,  i -> i+1).limit(20).forEach(System.out::println);
		
		Path path = Paths.get("C:/Dev/AWS/instance details.txt");
		try {
			
			"asd".chars().map(x -> (char)x).mapToObj(i -> (Integer)i).collect(Collectors.toList());
			System.out.println(Files.lines(path).flatMap(line -> line.chars().mapToObj(c -> (int)c) )
				.reduce(Integer::sum).get());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println();
		ArrayList<String> asd = new ArrayList<String>(); 
		asd.forEach(x -> {
			System.out.println();;
		});
		asd.sort((x1, x2) -> x2.length() - x1.length() ); 
		Comparator<String> aa = new Comparator<String>() {
			
			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				return 0;
			}
		};	
		asd.sort(aa);
		Collections.sort(asd);
		
		ArrayList<Ff> ddd = new ArrayList<Ff>();
		Collections.sort(ddd);
		
		
		ddd.sort((d1, d2) -> d2.i - d1.i);
		ddd.sort((d1, d2) -> d2.i - d1.i);
		
		ddd.sort((Ff d1, Ff d2) -> (d2.i-d1.i) );
		
		Ff f1 = new Ff();
		Ff f2 = new Ff();
		int i = f2.s.compareTo(f1.s);
		
		ddd.stream().map( (f) -> f).collect(Collectors.toList());
		
		HashMap<String, Integer> mmm = new HashMap<String, Integer>();
		mmm.entrySet().stream().map(e -> {
			return e.getKey();
		});
		
		ddd.stream().map(Ff::called) ;
		ddd.stream().map(f -> new Ff("")) ;
		ddd.stream().map(f -> new Ff("")) ;
		ddd.stream().map(Ff::new) ;


		
	}

}

class Some {
	int i;
	public int getI() {
		return this.i;
	}
	public Some(int i) {
		this.i = i;
	}
}

class Ff  implements Comparable<Ff>{

	int i;
	String s;

	@Override
	public int compareTo(Ff f1) {
		return f1.i - this.i;
	}

	public Ff() {
		super();
	}
	
	public Ff(String ss) {
		this.s = ss;
	}
	
	
	public Ff(Ff ss) {
		this.s = ss.s;
		this.i = ss.i;
	}
	
	public static Ff called(Ff ff) {
		return ff;
	}
	
}