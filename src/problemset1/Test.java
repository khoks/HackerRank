package problemset1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.*;
import java.util.function.Consumer;


public class Test {


    static String[] crosswordPuzzle(String[] crossword, String words) {
        
        char[][] crossChars = new char[10][10];

        HashSet<Blank> placeHolders = new HashSet<Blank>(); 
        
        String[] wordArray = words.split(";");
        char[][] wordChars = new char[wordArray.length][];
        
        for(int i = 0 ; i < wordArray.length ; i++) {
        	wordChars[i] = wordArray[i].toCharArray();
        }
        
        Intersect intersection = null;
        int xIndex = 0;
        for(int i = 0 ; i < 10 ; i++) {
        	for(int j = 0; j < 10 ; j++) {
                crossChars[i][j] = crossword[i].toCharArray()[j];
                if(intersection == null && crossChars[i][j] == '-') { 
                	if(i < 10 && crossChars[i+1][j] == '-') {
                		intersection = new Intersect(new Point(i,j), true);
                	} else {
                		intersection = new Intersect(new Point(i,j), false);
                	}
                }
            }
        }
        
        Blank startingBlank = figureOutPlaceholder(intersection ,crossChars,null, placeHolders);
        
        figureOutCombination(startingBlank, crossChars, placeHolders, wordChars);
        
        return null;
    }

	private static void figureOutCombination(Blank startingBlank, char[][] crossChars, HashSet<Blank> placeHolders,
			char[][] wordChars) {
		
		
	}

	static Blank figureOutPlaceholder(Intersect intersection, char[][] crossChars, Blank parent, HashSet<Blank> placeHolders) { 
    	int v = intersection.intersection.i;
    	int h = intersection.intersection.j;
    	boolean direction = intersection.direction;
    	int minus = v;
		int plus = v;
		ArrayList<Intersect> intersects = new ArrayList<Intersect>();
		Blank tempBlank;
		if(direction) {//vertical
    		while(plus < 10) {
    			plus++;
    			if(crossChars[plus][h] == '-') {
    				if(checkIntersection(plus,h, !direction, crossChars)) {
    					intersects.add(new Intersect(new Point(plus, h), !direction));
    				}
    				continue;
    			}
    			plus--;
    			break;
    		}
    		while(minus > 0) {
    			minus--;
    			if(crossChars[minus][h] == '-') {
    				if(checkIntersection(minus,h, !direction, crossChars)) {
    					intersects.add(new Intersect(new Point(minus, h), !direction));
    				}
    				continue;
    			}
    			minus++;
    			break;
    		}
    		
    	} else { //horizontal
    		while(plus < 10) {
    			plus++;
    			if(crossChars[v][plus] == '-') {
    				if(checkIntersection(v,plus, !direction, crossChars)) {
    					intersects.add(new Intersect(new Point(v, plus), !direction));
    				}
    				continue;
    			}
    			plus--;
    			break;
    		}
    		while(minus > 0) {
    			minus--;
    			if(crossChars[v][minus] == '-') {
    				if(checkIntersection(v,minus, !direction, crossChars)) {
    					intersects.add(new Intersect(new Point(v, minus), !direction));
    				}
    				continue;
    			}
    			minus++;
    			break;
    		}
    	}
		Blank newPlaceHolder = null;
		if(direction) {
			newPlaceHolder = new Blank((plus - minus) + 1, new Point(minus, h), new Point(plus, h));
		} else {
			newPlaceHolder = new Blank((plus - minus) + 1, new Point(v, minus), new Point(v, plus));
		}
		if(!ifExists(placeHolders, newPlaceHolder)) {
			placeHolders.add(newPlaceHolder);
			for(Intersect intersect : intersects) {
				tempBlank = figureOutPlaceholder(intersect ,crossChars,newPlaceHolder, placeHolders);
				if(tempBlank != null) {
					newPlaceHolder.orderedIntersects.add(tempBlank);
				}
			}
		} else {
			newPlaceHolder = null;
		}
    	
    	return newPlaceHolder;
    }
    
    static boolean ifExists(HashSet<Blank> placeHolders, Blank newPlaceHolder) {
    	for(Blank b : placeHolders) {
    		if(b.equals(newPlaceHolder)) 
    			return true;
    	}
    	return false;
    }
    
    
     static boolean checkIntersection(int v, int h, boolean direction, char[][] crossChars) {
		if(direction) {
			if(v < 10 && crossChars[v+1][h] == '-') {
				return true;
			} else if(v > 0 && crossChars[v-1][h] == '-') {
				return true;
			}
		} else {
			if(h < 10 && crossChars[v][h+1] == '-') {
				return true;
			} else if(h > 0 && crossChars[v][h-1] == '-') {
				return true;
			}
		}
		return false;
	}


	static class Blank {
        public Blank parent;
    	public ArrayList<Blank> orderedIntersects;
        public int length;
        public Point start;
        public Point end;
        char[] word;
        public Blank(int length, Point start, Point end) {
            this.length = length;
            this.start = start;
            this.end = end;
            this.parent = null;
            orderedIntersects = new ArrayList<Test.Blank>();
        }
        
        public int hashCode() {
        	return (this.start.i*1000) + (this.start.j*100) + (this.end.i*10) + this.end.j;
        }
        
        public boolean equals(Blank input) {
        	return (this.start.i == input.start.i && this.start.j == input.start.j &&
        			this.end.i == input.end.i && this.end.j == input.end.j);
        }
    }
	
	static class Intersect {
		Point intersection;
		boolean direction;
		public Intersect(Point intersection, boolean direction) {
			this.intersection = intersection;
			this.direction = direction;
		}
	}
     
     static class Point {
    	 int i;
    	 int j;
    	 public Point(int i, int j) {
    		 this.i = i;
    		 this.j = j;
    	 }
     }

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] crossword = new String[10];

		/*
		 * for (int i = 0; i < 10; i++) { String crosswordItem = scanner.nextLine();
		 * crossword[i] = crosswordItem; }
		 */

        crossword[0] = "++++++++++";
        crossword[1] = "+------+++";
        crossword[2] = "+++-++++++";
        crossword[3] = "+++-++++++";
        crossword[4] = "+++-----++";
        crossword[5] = "+++-++-+++";
        crossword[6] = "++++++-+++";
        crossword[7] = "++++++-+++";
        crossword[8] = "++++++-+++";
        crossword[9] = "++++++++++";
        
        String words = "POLAND;LHASA;SPAIN;INDIA";

        String[] result = crosswordPuzzle(crossword, words);

		/*
		 * for (int i = 0; i < result.length; i++) { bufferedWriter.write(result[i]);
		 * 
		 * if (i != result.length - 1) { bufferedWriter.write("\n"); } }
		 */

        //bufferedWriter.newLine();

        //bufferedWriter.close();

        scanner.close();
    }
	
    // Complete the commonChild function below.
    static int commonChild(String s1, String s2) {
    	int maxSeqLength = 0;
    	int tempMaxSeqLength = 0;
    	for(int i = 0 ; i < s1.length() ; i++) {
    		tempMaxSeqLength = countCommonSequenceLength(i, 0, 0, s1, s2);
    		if(tempMaxSeqLength > maxSeqLength) {
    			maxSeqLength = tempMaxSeqLength;
    		}
    	}
    	return maxSeqLength;
    }
    
    static int countCommonSequenceLength(int startIndexS1, int startIndexS2, int currentCount, String s1, String s2) {
    	if(startIndexS1 >= s1.length() || startIndexS2 >= s2.length()) {
    		return currentCount;
    	}
    	int nextIndex = s2.indexOf(s1.substring(startIndexS1, startIndexS1 + 1), startIndexS2);
    	int maxSeqLength = currentCount;
    	int tempStartIndexS1 = startIndexS1;
    	int tempStartIndexS2 = startIndexS2;
    	int tempMaxSeqLength = 0;
    	if(nextIndex > -1) {
    		maxSeqLength++;
    		while(nextIndex > -1) {
    			tempStartIndexS1++;
    			tempMaxSeqLength = countCommonSequenceLength(startIndexS1 + 1, nextIndex + 1, currentCount + 1, s1, s2);
    			if(tempMaxSeqLength > maxSeqLength) {
    				maxSeqLength = tempMaxSeqLength;
    			}
	    		nextIndex = s2.indexOf(s1.substring(startIndexS1, startIndexS1 + 1), nextIndex + 1);
	    	}
    	} else {
    		tempMaxSeqLength = countCommonSequenceLength(startIndexS1 + 1, startIndexS2, currentCount, s1, s2);
    		if(tempMaxSeqLength > maxSeqLength) {
    			maxSeqLength = tempMaxSeqLength;
    		}
    	}
    	return maxSeqLength;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void mainasdsa(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s1 = "WEWOUCUIDGCGTRMEZEPXZFEJWISRSBBSYXAYDFEJJDLEBVHHKS";

        String s2 = "FDAGCXGKCTKWNECHMRXZWMLRYUCOCZHJRRJBOAJOQJZZVUYXIC";

        int result = commonChild(s1, s2);
        
        System.out.println(result);

        //bufferedWriter.write(String.valueOf(result));
        //bufferedWriter.newLine();

        //bufferedWriter.close();

        scanner.close();
        
    }
	
	
	static long substrCount(int n, String s) {
        int stage = 0;
        char[] cArr = s.substring(0,n).toCharArray();
        long specials  = 0 ;
        ArrayList<Repeater> repeaters = new ArrayList<Repeater>();
        int currentChar = 0;
        
        for(int c : cArr) {
        	if(c != currentChar) {
        		if(repeaters.size() > 0) {
        			specials += ((repeaters.get(repeaters.size()-1).count)*(repeaters.get(repeaters.size()-1).count + 1))/2;
        			if( repeaters.size()> 2 && repeaters.get(repeaters.size()-2).count == 1 && repeaters.get(repeaters.size()-3).c == repeaters.get(repeaters.size()-1).c) {
        				if(repeaters.get(repeaters.size()-3).count < repeaters.get(repeaters.size()-1).count ) {
        					specials += repeaters.get(repeaters.size()-3).count;
        				} else {
        					specials += repeaters.get(repeaters.size()-1).count;
        				}
        			}
        		}
        		currentChar = c;
        		repeaters.add(new Repeater(c, 1));
        	} else {
        		repeaters.get(repeaters.size()-1).count = repeaters.get(repeaters.size()-1).count + 1;
        	}
        }
        if(repeaters.size() > 0) {
        	specials += ((repeaters.get(repeaters.size()-1).count)*(repeaters.get(repeaters.size()-1).count + 1))/2;
        }
        if(repeaters.size() > 2) {
        	if( repeaters.get(repeaters.size()-2).count == 1 && repeaters.get(repeaters.size()-3).c == repeaters.get(repeaters.size()-1).c) {
				if(repeaters.get(repeaters.size()-3).count < repeaters.get(repeaters.size()-1).count ) {
					specials += repeaters.get(repeaters.size()-3).count;
				} else {
					specials += repeaters.get(repeaters.size()-1).count;
				}
			}
        }
        
        
        return specials;
    }
	
	static class Repeater {
		public int c;
		public long count;
		public Repeater(int c, long count) {
			this.c = c;
			this.count = count;
		}
	}

	
	//aaaabaaaabbbccdcccdab
	//aaaa b aa cccc bbb cc d ccc d a b
	//aaaabbbaccccbbbccdcccdab
	//aaaabaacccc
	
	static long substrCount1(int n, String s) {
        int stage = 0;
        char[] cArr = s.toCharArray();
        int index = 0;
        long specials = 0;
        long outlier = 0;
        for(int c : cArr) {
            stage++;
            specials++;
            System.out.println(c);
            outlier = 0;
            for(index = stage ; index < cArr.length ; index++) {
                if(cArr[index] == c) {
                    if(outlier == 0) {
                        specials++;
                        System.out.println(s.substring((stage-1), index +1));
                    } else {
                        if( (index - outlier) < (outlier - (stage-1)) ) {
                            continue;
                        } else if( (index - outlier) == (outlier - (stage-1)) ) {
                            specials++;
                            System.out.println(s.substring((stage-1), index+1));
                            break;
                        }
                    }
                } else {
                    if(outlier == 0) {
                        outlier = index;
                    } else {
                        break;
                    }
                }
            }
        }
        return specials;
    }

    public static void mainsadsadsad(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
    	
        int n = 23;

        String s = "aaaabaaccccbbbccdcccdab";
        
        long result = substrCount(n, s);

        System.out.println(result);
        //bufferedWriter.write(String.valueOf(result));
        //bufferedWriter.newLine();

        //bufferedWriter.close();

        scanner.close();
    }
	
	public static Consumer<String> returnConsumer() {
		return x -> System.out.println(x.length());
	}
	
	public static void mainasd(String[] args) {
		Map<Integer, Integer> m = new HashMap<Integer, Integer>();
		int i;
		Integer tempInt;
		for(i = 0 ; i < 100 ; i ++) {
			tempInt = new Double(Math.floor(Math.random()*1000)).intValue();
			m.put(tempInt, null != m.get(tempInt)?m.get(tempInt) + 1:1);
			if(m.get(tempInt) > 0)
				System.out.println(tempInt + " - " +  m.get(tempInt));
		}
	}
	
	public static void main2(String[] args) {
		try {
		
			Iterator<NetworkInterface> networkInterfaceIterator = NetworkInterface.getNetworkInterfaces().asIterator();
			while(networkInterfaceIterator.hasNext()) {
				NetworkInterface n = networkInterfaceIterator.next();
				///if(n.getName().equals("wlan5")){
					
					//System.out.println(n.getInterfaceAddresses());
					Iterator<InetAddress> i = n.getInetAddresses().asIterator();
					while(i.hasNext()) {
						InetAddress inet = i.next();
						if(inet.isSiteLocalAddress()) {
							System.out.println("***************************");
							System.out.println(n.getName());
							System.out.println(inet.getHostAddress());
							System.out.println("***************************");
						}
						
					}
					
					
				//}
			}
			//System.out.println(InetAddress.getLocalHost());
			//System.out.println(InetAddress.getLoopbackAddress());
		
			URL whatismyip = new URL("http://checkip.amazonaws.com");
			BufferedReader in = new BufferedReader(new InputStreamReader(
			                whatismyip.openStream()));

			String ip = in.readLine(); //you get the IP as a String
			System.out.println(ip);
			
			if (in != null) {
				in.close();
            }
		
		} catch (Exception e) {e.printStackTrace();}
	}

}
