/**
 * Koristeći datu klasu Stek, napisati metod koji:
 * a) iz fajla učitava elemente tipa int u strukturu Stek,
 * b) iz prosledjene instance Stek odstranjuje dostupne elemente dok su
 *    deljivi sa 4 ili nestane elemenata,
 * c) ispisuje sve elemente u strukturi Stek na ekran.
 * 
 * Glavni program treba da pokaže upotrebu ovih metoda. Obratiti pažnju
 * na stil programiranja.
 */
 
class Z01Pr3bP02 {
	
	public static Stek<Integer> ucitajStek(String f) {
		
		Stek<Integer> stek = new Stek<>();
		
		if (Svetovid.testIn(f)) {
			
			while (Svetovid.in(f).hasMore()) {
				
				int e = Svetovid.in(f).readInt();
				stek.stavi(e);
			}
			
			Svetovid.in(f).close();
		}
		
		return stek;
	}
	
	
	public static void metodZadatka(Stek<Integer> stek) {
		
		if (!stek.jePrazan()) {
			
			int e = stek.vrh();
			
			while (!stek.jePrazan() && e % 4 == 0) {
				
				stek.skiniVrh();
				e = stek.vrh();
			}
		}
	}
	
	
	public static void stampaj(Stek<Integer> stek) {
		
		if (stek.jePrazan())
			return;
			
		Stek<Integer> pom = new Stek<>();
		
		String output = "Stek [ ";
		
		while (!stek.jePrazan()) {
			
			output += stek.vrh() + " ";
			pom.stavi(stek.skiniVrh());
		}
		
		while (!pom.jePrazan())
			stek.stavi(pom.skiniVrh());
			
		System.out.println(output + "]");
	}
	
	
	public static void main(String[] args) {
		
		Stek<Integer> stek = ucitajStek("brojevi.txt");
		
		metodZadatka(stek);
		
		stampaj(stek);
	}
	
}

/**
 * Tip podataka stek, koji omogućava skladištenje podataka u skladu sa principom
 * "poslednji unutra, prvi napolje".
 * 
 * <p>
 * Implementacija koristi niz, te je u skladu sa tim ograničena veličina steka
 * koji se koristi i moguće je da će operacija za dodavanje elemenata baciti
 * izuzetak ukoliko nema mesta.
 * </p>
 * 
 * @version v1.0.0
 * 
 * @param <T>
 *            Tip podataka koji će se čuvati u konkretnoj instanci steka.
 */
public class Stek<T> {
	/**
	 * Separator vrednosti u {@code toString} metodu: {@value} .
	 */
	public static final String SEPARATOR = ", ";

	// indeks prvog slobodnog elementa na steku
	private int popunjeno;

	// niz u kome se skladiste elementi
	private T[] elementi;

	/**
	 * Veličina stekova za koje nije prosledjen parametar o veličini ({@value}
	 * ).
	 */
	public static final int PODRAZUMEVANA_VELICINA = 100;

	/**
	 * Kreira novi Stek podrazumevane veličine {@value #PODRAZUMEVANA_VELICINA}.
	 */
	public Stek() {
		this(PODRAZUMEVANA_VELICINA);
	}

	/**
	 * Kreira nov Stek zadate velicine.
	 * 
	 * @param n
	 *            maksimalan broj elemenata koji će ovaj stek moći da primi.
	 */
	// pozeljno koristiti Suppress da kompajliranje ne prijavljuje upozorenja
	@SuppressWarnings("unchecked")
	public Stek(int n) {
		popunjeno = 0;
		elementi = (T[]) (new Object[n]);
	}

	/**
	 * Vraća da li je stek prazan.
	 * 
	 * @return da li je stek prazan
	 */
	public boolean jePrazan() {
		return popunjeno == 0;
	}

	/**
	 * Vraća da li je stek pun.
	 * 
	 * @return da li je stek pun
	 */
	public boolean jePun() {
		return popunjeno == elementi.length;
	}

	/**
	 * Vraća vrednost elementa na vrhu steka. Ukoliko je stek prazan baca
	 * izuzetak.
	 * 
	 * @return vrednost elementa na vrhu steka
	 */
	public T vrh() {
		if (jePrazan()) {
			throw new IllegalStateException("Stek je prazan");
		} else
			return elementi[popunjeno - 1];
	}

	/**
	 * Skida element sa vrha steka i vraća ga. Ukoliko je stek prazan baca se
	 * izuzetak.
	 * 
	 * @return vrednost elementa koji je bio na vrhu steka
	 */
	public T skiniVrh() {
		if (jePrazan()) {
			throw new IllegalStateException("Stek je prazan");
		} else
			popunjeno--;
		return elementi[popunjeno];
	}

	/**
	 * Ubacuje prosleđeni element na vrh steka. Ukoliko je stek već pun baca se
	 * izuzetak.
	 * 
	 * @param x
	 *            element koji će biti ubačen na vrh steka
	 */
	public void stavi(T x) {
		if (jePun()) {
			throw new IllegalStateException("Stek je pun");
		} else {
			elementi[popunjeno] = x;
			popunjeno++;
		}
	}

	/**
	 * Vraća String reprezentaciju ovog Steka. Reprezentacija će sadržati
	 * najviše 4 elementa sa steka, tačnije najviše prva dva i poslednja dva,
	 * razdvojenih sa {@value #SEPARATOR}, a ukoliko ima više od 4 elementa biće
	 * dodato i "..." između prvih i poslednjih elemenata.
	 */
	public String toString() {
		String rez = "Stek: ";
		if (jePrazan()) {
			rez += "prazan";
		} else {
			rez += elementi[popunjeno - 1];
			if (popunjeno > 1) {
				int sledeci = popunjeno - 2;
				rez += SEPARATOR + elementi[sledeci];
				if (popunjeno > 2) {
					if (popunjeno > 4) {
						rez += SEPARATOR + "...";
					}
					if (popunjeno > 3) {
						rez += SEPARATOR + elementi[1];
					}
					rez += SEPARATOR + elementi[0];
				}
			}
		}
		return rez;
	}
}
