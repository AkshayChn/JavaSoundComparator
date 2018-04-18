/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soundcompare;
import java.io.*;
import java.util.*;
import java.lang.Math;

/**
 *
 * @author AkshayChn
 */

public class ComparatorModule
{
	
	public static void main(String[] args) throws IOException, WavFileException{
			
			WavFile wwf2 = WavFile.openWavFile(new File(<File Path>));
			WavFile wwf1 = WavFile.openWavFile(new File(<File Path>));
			
			
			Compare_dir(wwf1,wwf2);
			//Compare_bitWiseShift(wwf1,wwf2);
			//Compare_dirRelative(wwf1,wwf2);
			//Compare_bitWiseShiftRelative(wwf1,wwf2);
	}
	
	public static void Compare_dir(WavFile W1, WavFile W2)
	{
		try
		{
			// Open the wav file specified as the first argument
			WavFile wf1 = W1 ;
			WavFile wf2 = W2;
			WavFile small;
			WavFile big;
			
			long TotFramesSmall;
			long TotFramesBig;
			boolean wf1isbig;
			
			if(wf1.getNumFrames() >= wf2.getNumFrames()){
				TotFramesSmall = wf2.getNumFrames();
			TotFramesBig = wf1.getNumFrames();
			wf1isbig = true; big = wf1; small = wf2;
			}
			else{TotFramesSmall = wf1.getNumFrames();
			TotFramesBig = wf2.getNumFrames(); wf1isbig = false; big = wf2; small = wf1;}
			
			System.out.println("\nWavefile 1 details: \n");
			// Display information about the wav file
			wf1.display();
			System.out.println("\nWavefile 2 details: \n");
			wf2.display();
			
			
			
			


			
			
			int numChannelssmall = small.getNumChannels();
			int numChannelsbig = big.getNumChannels();

			// Create a buffersmall of 100 frames
			

			

			
			
			long parDiff = 0;
			
			long[] buffersmall = new long[100 * numChannelssmall];
			long[] bufferbig = new long[100 * numChannelsbig];
			int framesReadsmall;
			int framesReadbig;

			do
			{
				// Read frames into buffersmall
				framesReadsmall = small.readFrames(buffersmall, 100);
				framesReadbig = big.readFrames(bufferbig, 100);


//
				//System.out.println(framesReadsmall);
				
				// Loop through frames and look for minimum and maximum value
				for (int s=0 ; s<framesReadsmall * numChannelssmall ; s++)
				{
					
						parDiff = parDiff + Math.abs(buffersmall[s] - bufferbig[s]);			
				}
			}
			while (framesReadsmall != 0);
			
			

			long leastdiff = parDiff;
			long Totalbits2 = small.getNumFrames()*6553;
			long f = Totalbits2 - leastdiff;
			System.out.println("\n \n Comparing the files directly bit by bit with no offset. \nPercentage Similarity between the two files is: \n"+(f*100)/Totalbits2 +"%");

			
			
			
			
			
			
			
			wf1.close();
			wf2.close();
			small.close();
			big.close();
		}
		catch (Exception e)
		{
			System.err.println(e);
		}

		
	}
	public static void Compare_dirRelative(WavFile W1, WavFile W2)
	{
		try
		{
			// Open the wav file specified as the first argument
			WavFile wf1 = W1 ;
			WavFile wf2 = W2;
			if(ExactMatch(wf1,wf2)==false){
			WavFile small;
			WavFile big;
			
			long TotFramesSmall;
			long TotFramesBig;
			boolean wf1isbig;
			
			if(wf1.getNumFrames() >= wf2.getNumFrames()){
				TotFramesSmall = wf2.getNumFrames();
			TotFramesBig = wf1.getNumFrames();
			wf1isbig = true; big = wf1; small = wf2;
			}
			else{TotFramesSmall = wf1.getNumFrames();
			TotFramesBig = wf2.getNumFrames(); wf1isbig = false; big = wf2; small = wf1;}
			
			System.out.println("\nWavefile 1 details: \n");
			// Display information about the wav file
			wf1.display();
			System.out.println("\nWavefile 2 details: \n");
			wf2.display();
			
			
			
			


			
			
			int numChannelssmall = small.getNumChannels();
			int numChannelsbig = big.getNumChannels();

			// Create a buffersmall of 100 frames
			

			

			
			
			long parDiff = 0;
			
			long[] buffersmall = new long[100 * numChannelssmall];
			long[] bufferbig = new long[100 * numChannelsbig];
			int framesReadsmall;
			int framesReadbig;

			do
			{
				// Read frames into buffersmall
				framesReadsmall = small.readFrames(buffersmall, 100);
				framesReadbig = big.readFrames(bufferbig, 100);


//
				//System.out.println(framesReadsmall);
				
				// Loop through frames and look for minimum and maximum value
				for (int s=1 ; s<framesReadsmall * numChannelssmall ; s++)
				{
					
						parDiff = parDiff + Math.abs(((buffersmall[s-1] - buffersmall[s])/(Math.abs(buffersmall[s-1])+1) )-((bufferbig[s-1]- bufferbig[s])/(Math.abs(bufferbig[s-1])+1)));			
				}
			}
			while (framesReadsmall != 0);
			
			

			long leastdiff = parDiff;
			long Totalbits2 = small.getNumFrames()*8;
			long f = Totalbits2 - leastdiff;
			System.out.println("\n \n Comparing the relative Amplitudes with no offset. \nPercentage Similarity between the two files is: \n"+(f*100)/Totalbits2 +"%");

			
			
			
			
			
			
			
			wf1.close();
			wf2.close();
			small.close();
			big.close();}
			else{
						System.out.println("\n \n Comparing the relative Amplitudes with no offset. \nPercentage Similarity between the two files is: \n 100%");

			}
		}
		catch (Exception e)
		{
			System.err.println(e);
		}

		
	}
	public static void Compare_bitWiseShift(WavFile W1, WavFile W2)
		{
		try
		{
			
			// Open the wav file specified as the first argument
			WavFile wf1 = W1 ;
			WavFile wf2 = W2;
			WavFile small;
			WavFile big;
			
			long TotFramesSmall;
			long TotFramesBig;
			boolean wf1isbig;
			
			if(wf1.getNumFrames() >= wf2.getNumFrames()){
				TotFramesSmall = wf2.getNumFrames();
			TotFramesBig = wf1.getNumFrames();
			wf1isbig = true; big = wf1; small = wf2;
			}
			else{TotFramesSmall = wf1.getNumFrames();
			TotFramesBig = wf2.getNumFrames(); wf1isbig = false; big = wf2; small = wf1;}
			
			System.out.println("\nWavefile 1 details: \n");
			// Display information about the wav file
			wf1.display();
			System.out.println("\nWavefile 2 details: \n");
			wf2.display();
			int offsetmax;
			ArrayList<Long> Differences = new ArrayList<>();
			if((((int)big.getNumFrames()*big.getNumChannels()) -((int)small.getNumFrames()*small.getNumChannels()))!=0){
			offsetmax = ((int)big.getNumFrames()*big.getNumChannels()) -((int)small.getNumFrames()*small.getNumChannels()) - 100;}
			else {offsetmax = 0;}
			int offset = 0;
			
			int numChannelssmall = small.getNumChannels();
			int numChannelsbig = big.getNumChannels();

			// Create a buffersmall of 100 frames
			
			for(int i = 0; i < offsetmax; i++)
			{

			
			
			long parDiff = 0;
			
			long[] buffersmall = new long[100 * numChannelssmall];
			long[] bufferbig = new long[1000 * numChannelsbig];//bigger buffer
			int framesReadsmall;
			int framesReadbig;

			do
			{
				// Read frames into buffersmall
				framesReadsmall = small.readFrames(buffersmall, 100);
				framesReadbig = big.readFrames(bufferbig, 100);


//
				//System.out.println(framesReadsmall);
				
				// Loop through frames and look for minimum and maximum value
				for (int s=0 ; s<framesReadsmall * numChannelssmall ; s++)
				{
					
						parDiff = parDiff + Math.abs(buffersmall[s] - bufferbig[s]);			
				}
			}
			while (framesReadsmall != 0);
			
			
			if(parDiff != 0)Differences.add(parDiff);
			}
			
			

			long leastdiff = Collections.min(Differences);
			long Totalbits2 = small.getNumFrames()*6552;
			long f = Totalbits2 - leastdiff;
			System.out.println("\n \n Comparing the files by shifting Bits. \nPercentage Similarity between the two files is: \n"+(f*100)/Totalbits2 +"%");

			
			
			
			
			
			
			
			wf1.close();
			wf2.close();
			small.close();
			big.close();
		}
		catch (Exception e)
		{
			System.err.println(e);
		}

		
	}
	public static void Compare_bitWiseShiftRelative(WavFile W1, WavFile W2)
		{
		try
		{
			// Open the wav file specified as the first argument
			WavFile wf1 = W1 ;
			WavFile wf2 = W2;
			if(ExactMatch(wf1,wf2)==false){
			WavFile small;
			WavFile big;
			
			long TotFramesSmall;
			long TotFramesBig;
			boolean wf1isbig;
			
			if(wf1.getNumFrames() >= wf2.getNumFrames()){
				TotFramesSmall = wf2.getNumFrames();
			TotFramesBig = wf1.getNumFrames();
			wf1isbig = true; big = wf1; small = wf2;
			}
			else{TotFramesSmall = wf1.getNumFrames();
			TotFramesBig = wf2.getNumFrames(); wf1isbig = false; big = wf2; small = wf1;}
			
			System.out.println("\nWavefile 1 details: \n");
			// Display information about the wav file
			wf1.display();
			System.out.println("\nWavefile 2 details: \n");
			wf2.display();
			
			ArrayList<Long> Differences = new ArrayList<>();
			int offsetmax = ((int)big.getNumFrames()*big.getNumChannels()) -((int)small.getNumFrames()*small.getNumChannels()) - 100;
			int offset = 0;
			
			int numChannelssmall = small.getNumChannels();
			int numChannelsbig = big.getNumChannels();

			// Create a buffersmall of 100 frames
			
			for(int i = 0; i < offsetmax; i++)
			{

			
			
			long parDiff = 0;
			
			long[] buffersmall = new long[100 * numChannelssmall];
			long[] bufferbig = new long[1000 * numChannelsbig];//bigger buffer
			int framesReadsmall;
			int framesReadbig;

			do
			{
				// Read frames into buffersmall
				framesReadsmall = small.readFrames(buffersmall, 100);
				framesReadbig = big.readFrames(bufferbig, i, 100);


//
				//System.out.println(framesReadsmall);
				
				// Loop through frames and look for minimum and maximum value
				for (int s= 1; s<framesReadsmall * numChannelssmall ; s++)
				{
					
						parDiff = parDiff + Math.abs(((buffersmall[s-1] - buffersmall[s])/(Math.abs(buffersmall[s-1])+1) )-((bufferbig[s-1]- bufferbig[s])/(Math.abs(bufferbig[s-1])+1)));			
								
				}
				//System.out.println(parDiff);
				
			}
			while (framesReadsmall != 0);
			if(parDiff != 0)Differences.add(parDiff);
			}
			
			

			long leastdiff = Collections.min(Differences);
			long Totalbits2 = small.getNumFrames()*8;
			long f = Totalbits2 - leastdiff;
			System.out.println("\n \n Comparing the files by shifting Bits. \nPercentage Similarity between the two files is: \n"+(f*100)/Totalbits2 +"%");

			
			
			
			
			
			
			
			wf1.close();
			wf2.close();
			small.close();
			big.close();}
			else{
						System.out.println("\n \n Comparing the relative Amplitudes with no offset. \nPercentage Similarity between the two files is: \n 100%");

			}
		}
		catch (Exception e)
		{
			System.out.println("This comparison was not successfully made");
			System.err.println(e);
		}

		
	}
	public static boolean ExactMatch(WavFile W1, WavFile W2)
{
	boolean match=false;
		try
		{
			// Open the wav file specified as the first argument
			WavFile wf1 = W1 ;
			WavFile wf2 = W2;
			WavFile small;
			WavFile big;
			
			long TotFramesSmall;
			long TotFramesBig;
			boolean wf1isbig;
			
			if(wf1.getNumFrames() >= wf2.getNumFrames()){
				TotFramesSmall = wf2.getNumFrames();
			TotFramesBig = wf1.getNumFrames();
			wf1isbig = true; big = wf1; small = wf2;
			}
			else{TotFramesSmall = wf1.getNumFrames();
			TotFramesBig = wf2.getNumFrames(); wf1isbig = false; big = wf2; small = wf1;}
			
			System.out.println("\nWavefile 1 details: \n");
			// Display information about the wav file
			wf1.display();
			System.out.println("\nWavefile 2 details: \n");
			wf2.display();
			
			
			
			


			
			
			int numChannelssmall = small.getNumChannels();
			int numChannelsbig = big.getNumChannels();

			// Create a buffersmall of 100 frames
			

			

			
			
			long parDiff = 0;
			
			long[] buffersmall = new long[100 * numChannelssmall];
			long[] bufferbig = new long[100 * numChannelsbig];
			int framesReadsmall;
			int framesReadbig;

			do
			{
				// Read frames into buffersmall
				framesReadsmall = small.readFrames(buffersmall, 100);
				framesReadbig = big.readFrames(bufferbig, 100);


//
				//System.out.println(framesReadsmall);
				
				// Loop through frames and look for minimum and maximum value
				for (int s=0 ; s<framesReadsmall * numChannelssmall ; s++)
				{
					
						parDiff = parDiff + Math.abs(buffersmall[s] - bufferbig[s]);			
				}
			}
			while (framesReadsmall != 0);
			
			

			long leastdiff = parDiff;
			long Totalbits2 = small.getNumFrames()*6553;
			long f = Totalbits2 - leastdiff;
			
			if(((f*100)/Totalbits2)==100){
			match = true;}
			else{match = false;}
			
			
			
			
			
			
			
			wf1.close();
			wf2.close();
			small.close();
			big.close();
			
		}
		catch (Exception e)
		{
			System.err.println(e);
		}
		return match;
		
	}
}
