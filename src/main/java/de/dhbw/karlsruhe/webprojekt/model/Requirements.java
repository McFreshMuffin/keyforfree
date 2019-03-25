package de.dhbw.karlsruhe.webprojekt.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "REQUIREMENTS")
public class Requirements implements Serializable {
    
    @Id
    @Column(name = "REQ_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int reqId;
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "requirements", fetch = FetchType.LAZY)
    Games games;
    
    @Column(name = "PLATFORM_WINDOWS")
    private int platformWindows;
    
    @Column(name = "PLATFORM_LINUX")
    private int platformLinux;
    
    @Column(name = "PLATFORM_MAC")
    private int platformMac;
    
    @Column(name = "MIN_WINDOWS")
    private int haveMinPcReqs;
    
    @Column(name = "REC_WINDOWS")
    private int haveRecPcReqs;
   
    @Column(name = "MIN_LINUX")
    private int haveMinLinuxReqs;
    
    @Column(name = "REC_LINUX")
    private int haveRecLinuxReqs;
    
    @Column(name = "MIN_MAC")
    private int haveMinMacReqs;
    
    @Column(name = "REC_MAC")
    private int haveRecMacReqs;
    
    @Column(name = "MIN_WIN_TEXT")
    private String PCMinReqsText;
    
    @Column(name = "REC_WIN_TEXT")
    private String PCRecReqsText;
    
    @Column(name = "MIN_LIN_TEXT")
    private String LinuxMinReqsText;
    
    @Column(name = "REC_LIN_TEXT")
    private String LinuxRecReqsText;
    
    @Column(name = "MIN_MAC_TEXT")
    private String MacMinReqsText;
    
    @Column(name = "REC_MAC_TEXT")
    private String MacRecReqsText;
}
