package com.lsy;

import java.io.File;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Synthesizer;

public class PlaySound {

	/**
	 * @param args
	 */
	static // MIDI_note_number    Name in music    Notes
	// ----------------    -------------    --------------------------
	//  21                 A0               lowest note on an 88-key piano
	//   .                 .                .
	//   .                 .                .
	//   .                 .                .
	//  57                 A3               has a frequency of 220 Hertz
	//  58                 A3# = B3b
	//  59                 B3
	//  60                 C4               "middle C"; start of 4th octave
	//  61                 C4# = D4b
	//  62                 D4
	//  63                 D4# = E4b
	//  64                 E4
	//  65                 F4
	//  66                 F4# = G4b
	//  67                 G4
	//  68                 G4# = A4b
	//  69                 A4               "concert A"; has a frequency of 440 Hertz
	//  70                 A4# = B4b
	//  71                 B4
	//  72                 C5               "concert C"; start of 5th octave
	//  73                 C5# = D5b
	//   .                 .                .
	//   .                 .                .
	//   .                 .                .
	// 108                 C8               highest note on an 88-key piano

	MidiChannel channel;
	
	private static Sequencer sequencer;
	
	static int volume = 50;// between 0 et 127

	static int C = 60;
	static int D = 62;
	static int E = 64;
	static int F = 65;
	static int G = 67;
	static int A = 69;
	static int B = 71;

	static int duration = 400;


	// Play a single note of a specific duration (milisecond), with an instrument of volume.
	public static void playNote(int note, int duration, int instrument, int volume) {
		try {
			Synthesizer synthesizer = MidiSystem.getSynthesizer();
			synthesizer.open();
			MidiChannel channel = synthesizer.getChannels()[instrument];

			channel.noteOn(note, volume);
			Thread.sleep(duration);
			channel.noteOff(note, 50);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// Create an integer representing "octave" higher octaves than the input note.
	// i.e. C will become high C. if octaveHigher(C, 1).
	public static int octaveHigher(int note, int octave){
		return note + (octave * 12);
	}
	
	 /**
     * Try to set the seqencer up.
     */
    {
        try {
            sequencer = MidiSystem.getSequencer();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Create a new MidiPlayer object with a default
     * instrument specified by DEFAULT_INSTRUMENT - 
     * usually a piano sound.
     * @return 
     */
    public PlaySound()
    {
        channel = getChannel(0);
    }
    
    /**
     * Create a new MidiPlayer object with a specified
     * instrument.
     * @param instrument the instrument to use
     * @return 
     */
    public PlaySound(int instrument)
    {
        channel = getChannel(instrument);
    }
    
    /**
     * Change the instrument this MidiPlayer uses.
     * @param instrument the instrument to change to
     */
    public void setInstrument(int instrument)
    {
        channel.programChange(instrument);
    }
    
    /**
     * Get the instrument the MidiPlayer is using
     * at present.
     * @return the instrument in use.
     */
    public int getInstrument()
    {
        return channel.getProgram();
    }
	
	/**
     * Set how far each note is (relatively in semitones) above C.
     */
    private int getOffset(String note)
    {
        if(note.equalsIgnoreCase("C")) return 0;
        if(note.equalsIgnoreCase("C#")) return 1;
        if(note.equalsIgnoreCase("Db")) return 1;
        if(note.equalsIgnoreCase("D")) return 2;
        if(note.equalsIgnoreCase("D#")) return 3;
        if(note.equalsIgnoreCase("Eb")) return 3;
        if(note.equalsIgnoreCase("E")) return 4;
        if(note.equalsIgnoreCase("E#")) return 5;
        if(note.equalsIgnoreCase("F")) return 5;
        if(note.equalsIgnoreCase("F#")) return 6;
        if(note.equalsIgnoreCase("Gb")) return 6;
        if(note.equalsIgnoreCase("G")) return 7;
        if(note.equalsIgnoreCase("G#")) return 8;
        if(note.equalsIgnoreCase("Ab")) return 8;
        if(note.equalsIgnoreCase("A")) return 9;
        if(note.equalsIgnoreCase("A#")) return 10;
        if(note.equalsIgnoreCase("Bb")) return 10;
        if(note.equalsIgnoreCase("B")) return 11;
        if(note.equalsIgnoreCase("Cb")) return 11;
        else throw new RuntimeException();
    }
    /**
     * Play a note - this method doesn't turn the note
     * off after a specified period of time, the release
     * method must be called to do that.
     * @param note the note to play
     */
    public static void play(final int note)
    {
        channel.noteOn(note, 50);
    }
    
    /**
     * Release a note that was previously played. If this
     * note isn't on already, this method will do nothing.
     */
    public void release(final int note)
    {
        channel.noteOff(note, 50);
    }
    
    /**
     * Play a note for a certain amount of time.
     * @param note the integer value for the note to play
     * @param length the length to play the note (ms).
     */
    public static void play(final int note, final int length)
    {
        new Thread() {
            public void run() {
                channel.noteOn(note, 50);
                try {
                    Thread.sleep(length);
                }
                catch(InterruptedException ex) {}
                finally {
                    channel.noteOff(note, 50);
                }
                channel.noteOff(note, 50);
            }
        }.start();

    }
    
    /**
     * Release all notes smoothly. This can be called in the World.stopped()
     * method to ensure no notes are playing when the scenario has been 
     * stopped or reset.
     */
    public void turnAllOff()
    {
        try {
            channel.allNotesOff();
            //This would turn cut notes of immediately and suddenly:
            //channel.allSoundOff();
            sequencer.stop();
            sequencer.close();
        }
        catch(Exception ex) {}
    }
    
    /**
     * Get the MidiChannel object that this MidiPlayer class is using.
     * If you want to do some more advanced work with the midi channel, you
     * can use this method to get the MidiChannel object and then work with
     * it directly. The API for the MidiChannel class is available online
     * as part of the javax.sound.midi package.<br>
     * Examples of why you might want to use this - adjusting the speed
     * notes are played / released with, adding sustain, adding pitch
     * bend, soloing / muting individual channels - all are fairly advanced
     * features and as such are not included in this class as standard (to
     * keep things simple and avoid clutter.)
     * @return the MidiChannel object behind this MidiPlayer.
     */
    public MidiChannel getMidiChannel()
    {
        return channel;
    }
    
    
    /**
     * Play a Midi file.
     */
    public static void playMidiFile(String fileName)
    {
        try {
            Sequence sequence = MidiSystem.getSequence(new File(fileName));
            sequencer.open();
            sequencer.setSequence(sequence);
            sequencer.start();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Stop playing a Midi file.
     */
    public static void stopMidiFile()
    {
        sequencer.stop();
    }
    
    /**
     * Internal method to get the channel from the synthesizer in use.
     * @param instrument the instrument to load initially.
     */
    private MidiChannel getChannel(int instrument)
    {
        try {
            Synthesizer synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();
            for (int i=0; i<synthesizer.getChannels().length; i++) {
                synthesizer.getChannels()[i].controlChange(7, 127);
            }
            return synthesizer.getChannels()[instrument];
        }
        catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
