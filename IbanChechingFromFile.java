package ibanCheching;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class IbanChechingFromFile {

	private static ArrayList<String> list2 = new ArrayList<String>();
	private static String fileDirectory;

	public IbanChechingFromFile(ArrayList<String> list, String fileDirectory) {
		IbanChechingFromFile.list2 = list;
		IbanChechingFromFile.fileDirectory = fileDirectory;
	}

	private static String SavingFilePlace() {

		Path p = Paths.get(fileDirectory);
		String directory = p.getParent().toString();
		return directory;
	}

	private static String OriginalFileName() {

		String fileWithName = fileDirectory.substring(fileDirectory.lastIndexOf("\\") + 1);
		String[] strParts = fileWithName.split("\\.");

		String OriginalFileName = strParts[0];

		return OriginalFileName;
	}

	@SuppressWarnings("unused")
	public static boolean checkingIban() throws IOException {

		ArrayList<String> foundaz = new ArrayList<String>();
		for (String units : list2) {
			if (!units.isEmpty() && units != null) {
				String Regex = "^((NO)[0-9A-Z]{2}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{3}|(NO)[0-9A-Z]{13}|(BE)[0-9A-Z]{2}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}|(BE)[0-9A-Z]{14}|(DK|FO|FI|GL|NL)[0-9A-Z]{2}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{2}|(DK|FO|FI|GL|NL)[0-9A-Z]{16}|(MK|SI)[0-9A-Z]{2}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{3}|(MK|SI)[0-9A-Z]{17}|(BA|EE|KZ|LT|LU|AT)[0-9A-Z]{2}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}|(BA|EE|KZ|LT|LU|AT)[0-9A-Z]{18}|(HR|LI|LV|CH)[0-9A-Z]{2}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{1}|(HR|LI|LV|CH)[0-9A-Z]{19}|(BG|DE|IE|ME|RS|GB)[0-9A-Z]{2}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{2}|(BG|DE|IE|ME|RS|GB)[0-9A-Z]{20}|(GI|IL)[0-9A-Z]{2}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{3}|(GI|IL)[0-9A-Z]{21}|(AD|CZ|SA|RO|SK|ES|SE|TN)[0-9A-Z]{2}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}|(AD|CZ|SA|RO|SK|ES|SE|TN)[0-9A-Z]{22}|(PT)[0-9A-Z]{2}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{1}|(PT)[0-9A-Z]{23}|(IS|TR)[0-9A-Z]{2}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{2}|(IS|TR)[0-9A-Z]{24}|(FR|GR|IT|MC|SM)[0-9A-Z]{2}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{3}|(FR|GR|IT|MC|SM)[0-9A-Z]{25}|(AL|CY|HU|LB|PL)[0-9A-Z]{2}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}|(AL|CY|HU|LB|PL)[0-9A-Z]{26}|(MU)[0-9A-Z]{2}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{2}|(MU)[0-9A-Z]{28}|(MT)[0-9A-Z]{2}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{4}[ ][0-9A-Z]{3}|(MT)[0-9A-Z]{29})$";
				Pattern pat = Pattern.compile(Regex);
				if (units == null) {
					System.out.println("File verification failed");
					return false;

				} else {

					foundaz.add(units + ";" + pat.matcher(units).matches());
				}
			}
		}
		if (!foundaz.isEmpty()) {
			System.out.println("===== Verified file ============== Verified file ============================");
			WritingToFile(foundaz);
			for (String l : foundaz) {
				System.out.println(l);
			}
		}
		return false;
	}

	private static void WritingToFile(ArrayList<String> foundaz) throws IOException {
		String twoSlash = "\\";
		String FileExtension = ".out";

		String savingPlace = SavingFilePlace() + twoSlash + OriginalFileName() + FileExtension;

		System.out.println("Saved file: " + savingPlace + "\n");
		File file = new File(savingPlace);

		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter WriteToFile = new FileWriter(file.getAbsoluteFile());
		BufferedWriter Writer = new BufferedWriter(WriteToFile);

		for (String s : foundaz) {
			Writer.write(s + System.getProperty("line.separator"));
		}
		Writer.close();

	}
}
