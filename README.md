# inSynch
Test the offset in the clocks between two devices.

This is a little android app, that allows you to measure the time difference (offset) between an android device and a computer (Mac, Windows). It does not work between two androids though (yet). 

This is because although computers poll an external time-server to synchronise their internal clocks, their clock usually drifts overtime by a few milliseconds. If you are interested in sub-second synchronisation between two devices, this app gives you an estimate of the time difference or offset between the Android running inSynch and the other device.

inSynch implements an [NTP client](http://commons.apache.org/net/examples/ntp/NTPClient.java) following the Network Time Protocol.

inSynch requires that the 'other' device runs an NTP server – a protocol that returns the time, plus metadata, on the other device.

If you think something could be improved in terms of accuracy, let me know!

### Instructions

1. Setup an ad-hoc network from your android or another device. You can use the Android's tethering (hotspot).
1. Connect both device on the same network. If the Android is creating a hotspot (tethering), connect to it – it doesn't matter if you are actually online.
1. Find the ip of the other device, enter it on the appropriate field of inSynch
1. Click the button and wait. 
1. If doesn't work and:
  1. ... the other device is an Android/iPhone etc: I haven't got a solution.
  2. ... the other device is a Mac. Hm, so far I didn't have a problem.
  3. ... the other device is a Windows computer/tablet. Follow these instructions on how to [setup an NTP server on your system](http://blogs.interfacett.com/creating-standalone-ntp-server-windows) (require simple use of command line).  



