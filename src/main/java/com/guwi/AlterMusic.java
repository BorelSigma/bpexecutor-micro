package com.guwi;

import java.io.File;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import jm.util.Read;
import jm.util.Write;
import jm.audio.io.SampleIn;
import jm.util.AudioSampleInst;

import javax.jws.WebMethod;
import javax.jws.WebService;


@WebService
//(endpointInterface="com.guwi.AlterMusicWSInt", portName="AlterMusicWSIntPort", serviceName="AlterMusicWSInt")
public class AlterMusic
{
	
	protected final static String TMP_DIR = "./tmp/";
	protected String tmp_name ="";
	
	/////////////////////////////////////////////////////////////////////////////
	// Constructor
    /////////////////////////////////////////////////////////////////////////////	
	
	public AlterMusic()
	{
		File file = new File(TMP_DIR);
		if(!file.exists()) file.mkdir();
		
	}

	
	
	/////////////////////////////////////////////////////////////////////////////
	// Webmethod functions
    /////////////////////////////////////////////////////////////////////////////	
	
	@WebMethod
	public byte[] addFading(String file_name, byte[] data, float fading)
	{
		String tmp_file_name = this.upload_File(TMP_DIR+file_name, data);
		byte[] return_data = this.download_file(this.addFading(tmp_file_name, fading));
		this.deleteFile(file_name);
		this.deleteFile(tmp_file_name);
		return return_data;
	}
	
	@WebMethod
	public byte[] limiter(String file_name, byte[] data, float max_value)
	{
		String tmp_file_name = this.upload_File(TMP_DIR+file_name, data);
		byte[] return_data = this.download_file(this.limiter(tmp_file_name, max_value));
		this.deleteFile(file_name);
		this.deleteFile(tmp_file_name);
		return return_data;
	}
	
	@WebMethod
	public byte[] normalize(String file_name, byte[] data, float max_volume)
	{
		String tmp_file_name = this.upload_File(TMP_DIR+file_name, data);
		byte[] return_data = this.download_file(this.normalize(tmp_file_name, max_volume));
		this.deleteFile(file_name);
		this.deleteFile(tmp_file_name);
		return return_data;
	}
	
	@WebMethod
	public byte[] changeSampleRate(String file_name, byte[] data, int sample_rate)
	{
		String tmp_file_name = this.upload_File(TMP_DIR+file_name, data);
		byte[] return_data = this.download_file(this.changeSampleRate(tmp_file_name, sample_rate));
		this.deleteFile(file_name);
		this.deleteFile(tmp_file_name);
		return return_data;
	}
	
	@WebMethod
	public byte[] bither(String file_name, byte[] data, int sample_size)
	{
		System.out.println("Starte Bither fuer File "+file_name+" Size "+sample_size);
		String tmp_file_name = this.upload_File(TMP_DIR+file_name, data);
		byte[] return_data = this.download_file(this.bither(tmp_file_name, sample_size));
		this.deleteFile(file_name);
		this.deleteFile(tmp_file_name);
		return return_data;
	}
	
	@WebMethod
	public byte[] setNumberofChannels(String file_name, byte[] data, int channels)
	{
		String tmp_file_name = this.upload_File(TMP_DIR+file_name, data);
		byte[] return_data = this.download_file(this.setNumberofChannels(tmp_file_name, channels));
		this.deleteFile(file_name);
		this.deleteFile(tmp_file_name);
		return return_data;
	}
	
	
	/////////////////////////////////////////////////////////////////////////////
	// processing functions
    /////////////////////////////////////////////////////////////////////////////
		
	protected String limiter(String file_name, float max_value)
	{
		float[] sample_data = getSample_data(file_name);
		for(int i = 0; i < sample_data.length;i++)
		{
			if(sample_data[i] > max_value) sample_data[i] = max_value;
			if(sample_data[i] < -max_value) sample_data[i] = -max_value;
		}
		String new_name = AlterMusic.addToFileName(file_name, "_limittet_"+max_value);
		Write.audio(sample_data,new_name, getChannelsFor(file_name), getSampleRateFor(file_name), getSampleSizeFor(file_name));
		tmp_name = new_name;
		return new_name;
	}
	
	protected String normalize(String file_name, float max_volume)
	{
		float[] sample_data = getSample_data(file_name);
		for(int i=0; i < sample_data.length;i++)
		{
			if(Math.abs(sample_data[i]) > max_volume)
			{
				max_volume = Math.abs(sample_data[i]);
			}
			
		}
		float gain = 1.0f / max_volume;
		for(int i = 0; i < sample_data.length;i++)
		{
			sample_data[i] *= gain;
		}
		String new_name = AlterMusic.addToFileName(file_name, "_normalized_"+max_volume);
		Write.audio(sample_data,new_name, getChannelsFor(file_name), getSampleRateFor(file_name), getSampleSizeFor(file_name));
		tmp_name = new_name;
		return new_name;
	}
	
	protected String changeSampleRate(String file_name, int sample_rate)
	{
		float[] sample_data = getSample_data(file_name); 
		String new_name = AlterMusic.addToFileName(file_name,"_samplerat_"+sample_rate);
		Write.audio(sample_data,new_name, getChannelsFor(file_name), sample_rate, getSampleSizeFor(file_name));
		tmp_name = new_name;
		return new_name;
	}
	
	protected String bither(String file_name, int sample_size)
	{
		float[] sample_data = getSample_data(file_name); 
		String new_name = AlterMusic.addToFileName(file_name,"_bither_"+sample_size+"bit_");
		Write.audio(sample_data,new_name, getChannelsFor(file_name), getSampleRateFor(file_name), sample_size);
		tmp_name = new_name;
		return new_name;
	}
	
	protected String setNumberofChannels(String file_name, int channels)
	{
		float[] sample_data = getSample_data(file_name);
		String new_name = AlterMusic.addToFileName(file_name,"_channels_"+channels+"bit_");
		Write.audio(sample_data,new_name, channels, getSampleRateFor(file_name), getSampleSizeFor(file_name));
		tmp_name = new_name;
		return new_name;
	}
	
	protected String addFading(String file_name, float fading)
	{
		float[] sample_data = getSample_data(file_name);
		float fadeinc = fading / sample_data.length;
		for(int i = 0; i< sample_data.length;i++)
		{
			sample_data[i] *= (sample_data.length - i) * fadeinc;
		}
		String new_name = AlterMusic.addToFileName(file_name,"fading_"+fading);
		Write.audio(sample_data,new_name, getChannelsFor(file_name), getSampleRateFor(file_name), getSampleSizeFor(file_name));
		tmp_name = new_name;
		return new_name;
	}
	

	
	/////////////////////////////////////////////////////////////////////////////
	// Utility functions
    /////////////////////////////////////////////////////////////////////////////
	
	protected String upload_File(String file_name, byte[] data)
	{
		tmp_name = deleateDoubleTMPDIR(AlterMusic.addToFileName(file_name, "_"+new Double(Math.random()).toString()));
		try
		{
			FileOutputStream file_output_stream = new FileOutputStream(tmp_name);
			BufferedOutputStream buffered_output_stream = new BufferedOutputStream(file_output_stream);
			buffered_output_stream.write(data);
			buffered_output_stream.close();
			return tmp_name;
		}
		catch(Exception e)
		{
			System.out.println("Error in Upload");
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	protected byte[] download_file(String file_name)
	{
		try
		{
			
			File file = new File(file_name);
			System.out.println("Begin write data for file: "+file.getName());
			FileInputStream file_input_stream = new FileInputStream(file);
			BufferedInputStream buffered_input_stream = new BufferedInputStream(file_input_stream);
			byte[] data = new byte[(int) file.length()];
			buffered_input_stream.read(data);
			buffered_input_stream.close();
			System.out.println("done writing data");
			return data;
		}
		catch(Exception e)
		{
			System.out.println("Error in Download");
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	static protected String addToFileName(String file_name,String add)
	{
		String file_type = file_name.substring(file_name.lastIndexOf("."));
		String name = file_name.substring(0,file_name.lastIndexOf("."));
		return name+add+file_type;
	}
	
	public void clearTMPFolder()
	{
		File tmp_dir = new File(TMP_DIR);
		String[] list = tmp_dir.list();
		for(int i = 0; i < list.length;i++)
		{
			File file = new File(TMP_DIR+list[i]);
			file.delete();
		}
	}
	
	protected float[] getSample_data(String file_name)
	{
		return Read.audio(file_name);
	}
	
	protected String get_FileEnding(String filename)
	{
		return filename.substring(filename.lastIndexOf("."));
	}
	
	protected String deleateDoubleTMPDIR(String file_name)
	{
		if(file_name.startsWith(TMP_DIR+TMP_DIR)) file_name = file_name.replace(TMP_DIR+TMP_DIR, TMP_DIR);
		return file_name;
	}
	
	protected int getChannelsFor(String file_name)
	{
		return new AudioSampleInst(file_name).getChannels();
	}
	
	protected int getSampleRateFor(String file_name)
	{
		return new AudioSampleInst(file_name).getSampleRate();
	}
	
	protected int getSampleSizeFor(String file_name)
	{
		return new SampleIn(new AudioSampleInst(file_name), file_name).getBitResolution();
	}
	
	protected void deleteFile(String file_name)
	{
		File file = new File(file_name);
		file.delete();
	}
	
	public String getTampName()
	{
		return this.tmp_name;
	}

}
