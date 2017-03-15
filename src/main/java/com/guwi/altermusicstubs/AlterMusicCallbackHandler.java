
/**
 * AlterMusicCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.3  Built on : May 30, 2016 (04:08:57 BST)
 */

    package com.guwi.altermusicstubs;

    /**
     *  AlterMusicCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class AlterMusicCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public AlterMusicCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public AlterMusicCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for normalize method
            * override this method for handling normal response from normalize operation
            */
           public void receiveResultnormalize(
                    com.guwi.altermusicstubs.NormalizeResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from normalize operation
           */
            public void receiveErrornormalize(Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for setNumberofChannels method
            * override this method for handling normal response from setNumberofChannels operation
            */
           public void receiveResultsetNumberofChannels(
                    com.guwi.altermusicstubs.SetNumberofChannelsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from setNumberofChannels operation
           */
            public void receiveErrorsetNumberofChannels(Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for limiter method
            * override this method for handling normal response from limiter operation
            */
           public void receiveResultlimiter(
                    com.guwi.altermusicstubs.LimiterResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from limiter operation
           */
            public void receiveErrorlimiter(Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for changeSampleRate method
            * override this method for handling normal response from changeSampleRate operation
            */
           public void receiveResultchangeSampleRate(
                    com.guwi.altermusicstubs.ChangeSampleRateResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from changeSampleRate operation
           */
            public void receiveErrorchangeSampleRate(Exception e) {
            }
                
               // No methods generated for meps other than in-out
                
           /**
            * auto generated Axis2 call back method for addFading method
            * override this method for handling normal response from addFading operation
            */
           public void receiveResultaddFading(
                    com.guwi.altermusicstubs.AddFadingResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from addFading operation
           */
            public void receiveErroraddFading(Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bither method
            * override this method for handling normal response from bither operation
            */
           public void receiveResultbither(
                    com.guwi.altermusicstubs.BitherResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bither operation
           */
            public void receiveErrorbither(Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getTampName method
            * override this method for handling normal response from getTampName operation
            */
           public void receiveResultgetTampName(
                    com.guwi.altermusicstubs.GetTampNameResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getTampName operation
           */
            public void receiveErrorgetTampName(Exception e) {
            }
                


    }
    