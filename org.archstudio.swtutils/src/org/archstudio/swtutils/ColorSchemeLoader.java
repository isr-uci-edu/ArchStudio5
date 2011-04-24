package org.archstudio.swtutils;

import java.io.*;
import java.util.*;

import org.eclipse.swt.graphics.RGB;

// Generate new tetradic color schemes at:
// http://wellstyled.com/tools/colorscheme2/index-en.html

@SuppressWarnings({"unchecked"})
public class ColorSchemeLoader{
	
	protected ColorScheme[] defaultColorSchemes = null;
	
	private ColorSchemeLoader(){
	}
	
	public ColorScheme[] getDefaultColorSchemes(){
		if(defaultColorSchemes == null){
			defaultColorSchemes = loadDefaultColorSchemes();
		}
		return defaultColorSchemes;
	}
	
	protected ColorScheme[] loadDefaultColorSchemes(){
		InputStream is = ColorSchemeLoader.class.getClassLoader().getResourceAsStream("edu/uci/isr/widgets/swt/res/colorschemes.txt");
		return loadColorSchemes(is);
	}
	
	public ColorScheme[] loadColorSchemes(InputStream is){
		List schemeList = new ArrayList();
		
		String name = null;
		List colorArrayList = new ArrayList();
		
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			while(true){
				String line = br.readLine();
				if(line == null){
					if(name != null){
						RGB[][] colorArrays = (RGB[][])colorArrayList.toArray(new RGB[0][]);
						ColorScheme scheme = new ColorScheme(name, colorArrays);
						schemeList.add(scheme);
					}
					break;
				}
				line = line.trim();
				if(line.length() == 0){
					continue;
				}
				else if(line.startsWith("#")){
					continue;
				}
				else if(line.startsWith("&")){
					if(name != null){
						RGB[][] colorArrays = (RGB[][])colorArrayList.toArray(new RGB[0][]);
						ColorScheme scheme = new ColorScheme(name, colorArrays);
						schemeList.add(scheme);
					}
					name = line.substring(1).trim();
					colorArrayList.clear();
				}
				else{
					String[] colorStrings = line.split("\\b");
					List colorList = new ArrayList();
					for(int i = 0; i < colorStrings.length; i++){
						String colorString = colorStrings[i].trim();
						if(colorString.length() > 0){
							try{
								int colorRgb = Integer.parseInt(colorString, 16);
								
								int r = (colorRgb & 0xff0000) >> 16;
								int g = (colorRgb & 0x00ff00) >> 8;
								int b = (colorRgb & 0x0000ff);
								
								RGB rgb = new RGB(r, g, b);
								colorList.add(rgb);
							}
							catch(NumberFormatException nfe){
							}
						}
					}
					RGB[] colorArray = (RGB[])colorList.toArray(new RGB[0]);
					colorArrayList.add(colorArray);
				}
			}
		}
		catch(IOException e){
		}
		try{
			is.close();
		}
		catch(IOException ioe2){
		}
		ColorScheme[] colorSchemeArray = (ColorScheme[])schemeList.toArray(new ColorScheme[0]);
		return colorSchemeArray;
	}

	private static ColorSchemeLoader theInstance = null;

	public static synchronized ColorSchemeLoader getInstance(){
		if(theInstance == null){
			theInstance = new ColorSchemeLoader();
		}
		return theInstance;
	}

}
