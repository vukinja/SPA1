/**
 * Napisati program koji učitava n String-ova (n unosi korisnik), smeš-
 * ta ih u jednostruko povezanu listu, i štampa sadržaj liste. Listu f-
 * ormirati počevši od prazne liste, dodajući elemente s početka liste.
 * Program potom:
 * 
 * a) štampa sve elemente liste koji su duzi od tri slova,
 * b) utvrđuje broj pojava u listi elementa jednakog String-u b (kog u-
 *    nosi korisnik),
 * c) štampa poslednji element liste.
 */

class N03Z01P06 {
	
	public static void main(String[] args) {
		
		String s;
		int n;
		
		Lista lista = new Lista();
		
		n = Svetovid.in.readInt("Broj elemenata: ");
		
		for (int i = 0; i < n; i++) {
			s = Svetovid.in.readLine("Element #" + i + ": ");
			lista.dodajNaPocetak(s);
		}
		
		System.out.println(lista);
		
		lista.duziOd(3);
		
		s = Svetovid.in.readLine("Element za pretragu: ");
		lista.brojPojava(s);
		
		lista.stampajPoslednji();
	}
}

class Lista {
	
	class Element {
		
		String info;
		Element veza;
		
		
		public Element(String info) {
			this.info = info;
			this.veza = null;
		}
		
		
		public String toString() {
			return info;
		}
	}
	
	
	Element prvi;
	
	
	public void dodajNaPocetak(String info) {
		
		Element novi = new Element(info);
		
		novi.veza = prvi;
		prvi = novi;
	}
	
	
	public int brojPojava(String s) {
		
		if (prvi == null)
			return -1;
			
		int count = 0;
		Element tek = prvi;
		
		while (tek != null) {
			
			if (tek.info.equals(s))
				count++;
			
			tek = tek.veza;
		}
		
		System.out.println("Element '" + s + "' u listi puta: " + count);
		return count;
	}
	
	
	public String duziOd(int n) {
		
		String output = "[ Lista, duzi od " + n + ": ";
		
		Element tek = prvi;
		
		while (tek != null) {
			
			if (tek.info.length() > n)
				output += tek + " ";
				
			tek = tek.veza;
		}
		
		output += "]";
		
		System.out.println(output);
		return output;
	}
	
	
	public void stampajPoslednji() {
		
		if (prvi == null)
			return;
		
		if (prvi.veza == null)
			System.out.println(prvi);
			
		Element pret = prvi;
		
		while (pret.veza != null)
			pret = pret.veza;
			
		System.out.println(pret);
	}
	
	
	public String toString() {
		
		String output = "[ Lista : ";
		
		Element tek = prvi;
		
		while (tek != null) {
			
			output += tek + " ";
			tek = tek.veza;
		}
		
		return output + "]";
	}
}
