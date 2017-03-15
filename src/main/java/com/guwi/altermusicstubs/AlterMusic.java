

/**
 * AlterMusic.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.3  Built on : May 30, 2016 (04:08:57 BST)
 */

    package com.guwi.altermusicstubs;

    /*
     *  AlterMusic java interface
     */

    public interface AlterMusic {
          

        /**
          * Auto generated method signature
          * 
                    * @param normalize0
                
         */

         
                     public com.guwi.altermusicstubs.NormalizeResponse normalize(

                             com.guwi.altermusicstubs.Normalize normalize0)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param normalize0
            
          */
        public void startnormalize(

                com.guwi.altermusicstubs.Normalize normalize0,

                final com.guwi.altermusicstubs.AlterMusicCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param setNumberofChannels2
                
         */

         
                     public com.guwi.altermusicstubs.SetNumberofChannelsResponse setNumberofChannels(

                             com.guwi.altermusicstubs.SetNumberofChannels setNumberofChannels2)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param setNumberofChannels2
            
          */
        public void startsetNumberofChannels(

                com.guwi.altermusicstubs.SetNumberofChannels setNumberofChannels2,

                final com.guwi.altermusicstubs.AlterMusicCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param limiter4
                
         */

         
                     public com.guwi.altermusicstubs.LimiterResponse limiter(

                             com.guwi.altermusicstubs.Limiter limiter4)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param limiter4
            
          */
        public void startlimiter(

                com.guwi.altermusicstubs.Limiter limiter4,

                final com.guwi.altermusicstubs.AlterMusicCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param changeSampleRate6
                
         */

         
                     public com.guwi.altermusicstubs.ChangeSampleRateResponse changeSampleRate(

                             com.guwi.altermusicstubs.ChangeSampleRate changeSampleRate6)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param changeSampleRate6
            
          */
        public void startchangeSampleRate(

                com.guwi.altermusicstubs.ChangeSampleRate changeSampleRate6,

                final com.guwi.altermusicstubs.AlterMusicCallbackHandler callback)

            throws java.rmi.RemoteException;

     
       /**
         * Auto generated method signature for Asynchronous Invocations
         * 
         */
        public void  clearTMPFolder(
                com.guwi.altermusicstubs.ClearTMPFolder clearTMPFolder8

        ) throws java.rmi.RemoteException
        
        ;

        

        /**
          * Auto generated method signature
          * 
                    * @param addFading9
                
         */

         
                     public com.guwi.altermusicstubs.AddFadingResponse addFading(

                             com.guwi.altermusicstubs.AddFading addFading9)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param addFading9
            
          */
        public void startaddFading(

                com.guwi.altermusicstubs.AddFading addFading9,

                final com.guwi.altermusicstubs.AlterMusicCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param bither11
                
         */

         
                     public com.guwi.altermusicstubs.BitherResponse bither(

                             com.guwi.altermusicstubs.Bither bither11)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param bither11
            
          */
        public void startbither(

                com.guwi.altermusicstubs.Bither bither11,

                final com.guwi.altermusicstubs.AlterMusicCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param getTampName13
                
         */

         
                     public com.guwi.altermusicstubs.GetTampNameResponse getTampName(

                             com.guwi.altermusicstubs.GetTampName getTampName13)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param getTampName13
            
          */
        public void startgetTampName(

                com.guwi.altermusicstubs.GetTampName getTampName13,

                final com.guwi.altermusicstubs.AlterMusicCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        
       //
       }
    