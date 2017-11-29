package com.parkitalia.android.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.parkitalia.android.R;
import com.parkitalia.android.extra.Util;

import org.json.JSONException;
import org.json.JSONObject;

public class Information extends BaseActivity implements View.OnClickListener
{
    String licence="LICENSED APPLICATION END USER LICENSE AGREEMENT\n";
    String licencedetail="The Products transacted through the Service are licensed, not sold, to You for use only under the terms of this license, unless a Product is accompanied by a separate license agreement, in which case the terms of that separate license agreement will govern, subject to Your prior acceptance of that separate license agreement. The licensor (“Application Provider”) reserves all rights not expressly granted to You. The Product that is subject to this license is referred to in this license as the “Licensed Application.”\n" +
            "\n" +
            "a. Scope of License: This license granted to You for the Licensed Application by Application Provider is limited to a non-transferable license to use the Licensed Application on any Android or iPhone or iPod touch that You own or control and as permitted by the Usage Rules set forth in Section 9.b. of the App Store Terms and Conditions (the “Usage Rules”). This license does not allow You to use the Licensed Application on any iPod touch or iPhone that You do not own or control, and You may not distribute or make the Licensed Application available over a network where it could be used by multiple devices at the same time. You may not rent, lease, lend, sell, redistribute or sublicense the Licensed Application. You may not copy (except as expressly permitted by this license and the Usage Rules), decompile, reverse engineer, disassemble, attempt to derive the source code of, modify, or create derivative works of the Licensed Application, any updates, or any part thereof (except as and only to the extent any foregoing restriction is prohibited by applicable law or to the extent as may be permitted by the licensing terms governing use of any open sourced components included with the Licensed Application). Any attempt to do so is a violation of the rights of the Application Provider and its licensors. If You breach this restriction, You may be subject to prosecution and damages. The terms of the license will govern any upgrades provided by Application Provider that replace and/or supplement the original Product, unless such upgrade is accompanied by a separate license in which case the terms of that license will govern.\n" +
            "\n" +
            "b. Consent to Use of Data: You agree that Application Provider may collect and use technical data and related information, including but not limited to technical information about Your device, system and application software, and peripherals, that is gathered periodically to facilitate the provision of software updates, product support and other services to You (if any) related to the Licensed Application. Application Provider may use this information, as long as it is in a form that does not personally identify You, to improve its products or to provide services or technologies to You.\n" +
            "\n" +
            "c. Termination. The license is effective until terminated by You or Application Provider. Your rights under this license will terminate automatically without notice from the Application Provider if You fail to comply with any term(s) of this license. Upon termination of the license, You shall cease all use of the Licensed Application, and destroy all copies, full or partial, of the Licensed Application.\n" +
            "\n" +
            "d. Services; Third Party Materials. The Licensed Application may enable access to Application Provider’s and third party services and web sites (collectively and individually, \"Services\"). Use of the Services may require Internet access and that You accept additional terms of service.\n" +
            "\n" +
            "You understand that by using any of the Services, You may encounter content that may be deemed offensive, indecent, or objectionable, which content may or may not be identified as having explicit language, and that the results of any search or entering of a particular URL may automatically and unintentionally generate links or references to objectionable material. Nevertheless, You agree to use the Services at Your sole risk and that the Application Provider shall not have any liability to You for content that may be found to be offensive, indecent, or objectionable.\n" +
            "\n" +
            "Certain Services may display, include or make available content, data, information, applications or materials from third parties (“Third Party Materials”) or provide links to certain third party web sites. By using the Services, You acknowledge and agree that the Application Provider is not responsible for examining or evaluating the content, accuracy, completeness, timeliness, validity, copyright compliance, legality, decency, quality or any other aspect of such Third Party Materials or web sites. The Application Provider does not warrant or endorse and does not assume and will not have any liability or responsibility to You or any other person for any third-party Services, Third Party Materials or web sites, or for any other materials, products, or services of third parties. Third Party Materials and links to other web sites are provided solely as a convenience to You. Financial information displayed by any Services is for general informational purposes only and is not intended to be relied upon as investment advice. Before executing any securities transaction based upon information obtained through the Services, You should consult with a financial professional. Location data provided by any Services is for basic navigational purposes only and is not intended to be relied upon in situations where precise location information is needed or where erroneous, inaccurate or incomplete location data may lead to death, personal injury, property or environmental damage. Neither the Application Provider, nor any of its content providers, guarantees the availability, accuracy, completeness, reliability, or timeliness of stock information or location data displayed by any Services.\n" +
            "\n" +
            "You agree that any Services contain proprietary content, information and material that is protected by applicable intellectual property and other laws, including but not limited to copyright, and that You will not use such proprietary content, information or materials in any way whatsoever except for permitted use of the Services. No portion of the Services may be reproduced in any form or by any means. You agree not to modify, rent, lease, loan, sell, distribute, or create derivative works based on the Services, in any manner, and You shall not exploit the Services in any unauthorized way whatsoever, including but not limited to, by trespass or burdening network capacity. You further agree not to use the Services in any manner to harass, abuse, stalk, threaten, defame or otherwise infringe or violate the rights of any other party, and that the Application Provider is not in any way responsible for any such use by You, nor for any harassing, threatening, defamatory, offensive or illegal messages or transmissions that You may receive as a result of using any of the Services.\n" +
            "\n" +
            "In addition, third party Services and Third Party Materials that may be accessed from, displayed on or linked to from the Android or iPhone or iPod touch are not available in all languages or in all countries. The Application Provider makes no representation that such Services and Materials are appropriate or available for use in any particular location. To the extent You choose to access such Services or Materials, You do so at Your own initiative and are responsible for compliance with any applicable laws, including but not limited to applicable local laws. The Application Provider, and its licensors, reserve the right to change, suspend, remove, or disable access to any Services at any time without notice. In no event will the Application Provider be liable for the removal of or disabling of access to any such Services. The Application Provider may also impose limits on the use of or access to certain Services, in any case and without notice or liability.\n" +
            "\n" +
            "e. NO WARRANTY: YOU EXPRESSLY ACKNOWLEDGE AND AGREE THAT USE OF THE LICENSED APPLICATION IS AT YOUR SOLE RISK AND THAT THE ENTIRE RISK AS TO SATISFACTORY QUALITY, PERFORMANCE, ACCURACY AND EFFORT IS WITH YOU. TO THE MAXIMUM EXTENT PERMITTED BY APPLICABLE LAW, THE LICENSED APPLICATION AND ANY SERVICES PERFORMED OR PROVIDED BY THE LICENSED APPLICATION (\"SERVICES\") ARE PROVIDED \"AS IS\" AND “AS AVAILABLE”, WITH ALL FAULTS AND WITHOUT WARRANTY OF ANY KIND, AND APPLICATION PROVIDER HEREBY DISCLAIMS ALL WARRANTIES AND CONDITIONS WITH RESPECT TO THE LICENSED APPLICATION AND ANY SERVICES, EITHER EXPRESS, IMPLIED OR STATUTORY, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES AND/OR CONDITIONS OF MERCHANTABILITY, OF SATISFACTORY QUALITY, OF FITNESS FOR A PARTICULAR PURPOSE, OF ACCURACY, OF QUIET ENJOYMENT, AND NON-INFRINGEMENT OF THIRD PARTY RIGHTS. APPLICATION PROVIDER DOES NOT WARRANT AGAINST INTERFERENCE WITH YOUR ENJOYMENT OF THE LICENSED APPLICATION, THAT THE FUNCTIONS CONTAINED IN, OR SERVICES PERFORMED OR PROVIDED BY, THE LICENSED APPLICATION WILL MEET YOUR REQUIREMENTS, THAT THE OPERATION OF THE LICENSED APPLICATION OR SERVICES WILL BE UNINTERRUPTED OR ERROR-FREE, OR THAT DEFECTS IN THE LICENSED APPLICATION OR SERVICES WILL BE CORRECTED. NO ORAL OR WRITTEN INFORMATION OR ADVICE GIVEN BY APPLICATION PROVIDER OR ITS AUTHORIZED REPRESENTATIVE SHALL CREATE A WARRANTY. SHOULD THE LICENSED APPLICATION OR SERVICES PROVE DEFECTIVE, YOU ASSUME THE ENTIRE COST OF ALL NECESSARY SERVICING, REPAIR OR CORRECTION. SOME JURISDICTIONS DO NOT ALLOW THE EXCLUSION OF IMPLIED WARRANTIES OR LIMITATIONS ON APPLICABLE STATUTORY RIGHTS OF A CONSUMER, SO THE ABOVE EXCLUSION AND LIMITATIONS MAY NOT APPLY TO YOU.\n" +
            "\n" +
            "f. Limitation of Liability. TO THE EXTENT NOT PROHIBITED BY LAW, IN NO EVENT SHALL APPLICATION PROVIDER BE LIABLE FOR PERSONAL INJURY, OR ANY INCIDENTAL, SPECIAL, INDIRECT OR CONSEQUENTIAL DAMAGES WHATSOEVER, INCLUDING, WITHOUT LIMITATION, DAMAGES FOR LOSS OF PROFITS, LOSS OF DATA, BUSINESS INTERRUPTION OR ANY OTHER COMMERCIAL DAMAGES OR LOSSES, ARISING OUT OF OR RELATED TO YOUR USE OR INABILITY TO USE THE LICENSED APPLICATION, HOWEVER CAUSED, REGARDLESS OF THE THEORY OF LIABILITY (CONTRACT, TORT OR OTHERWISE) AND EVEN IF APPLICATION PROVIDER HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES. SOME JURISDICTIONS DO NOT ALLOW THE LIMITATION OF LIABILITY FOR PERSONAL INJURY, OR OF INCIDENTAL OR CONSEQUENTIAL DAMAGES, SO THIS LIMITATION MAY NOT APPLY TO YOU. In no event shall Application Provider’s total liability to you for all damages (other than as may be required by applicable law in cases involving personal injury) exceed the amount of fifty dollars ($50.00). The foregoing limitations will apply even if the above stated remedy fails of its essential purpose.\n" +
            "\n" +
            "g. You may not use or otherwise export or re-export the Licensed Application except as authorized by United States law and the laws of the jurisdiction in which the Licensed Application was obtained. In particular, but without limitation, the Licensed Application may not be exported or re-exported (a) into any U.S. embargoed countries or (b) to anyone on the U.S. Treasury Department's list of Specially Designated Nationals or the U.S. Department of Commerce Denied Person’s List or Entity List. By using the Licensed Application, you represent and warrant that you are not located in any such country or on any such list. You also agree that you will not use these products for any purposes prohibited by United States law, including, without limitation, the development, design, manufacture or production of nuclear, missiles, or chemical or biological weapons.\n" +
            "\n" +
            "h. The Licensed Application and related documentation are \"Commercial Items\", as that term is defined at 48 C.F.R. §2.101, consisting of \"Commercial Computer Software\" and \"Commercial Computer Software Documentation\", as such terms are used in 48 C.F.R. §12.212 or 48 C.F.R. §227.7202, as applicable. Consistent with 48 C.F.R. §12.212 or 48 C.F.R. §227.7202-1 through 227.7202-4, as applicable, the Commercial Computer Software and Commercial Computer Software Documentation are being licensed to U.S. Government end users (a) only as Commercial Items and (b) with only those rights as are granted to all other end users pursuant to the terms and conditions herein. Unpublished-rights reserved under the copyright laws of the United States.\n" +
            "\n" +
            "i. The laws of the State of California, excluding its conflicts of law rules, govern this license and your use of the Licensed Application. Your use of the Licensed Application may also be subject to other local, state, national, or international laws.\n";
    String policyHeader="Privacy Policy\n";
    String policydetail="This Privacy Policy (the \"Policy\") is inferred into the Terms and Conditions of the Website. Therefore the User must read and agree to this Policy prior to using the services provided by the Website. This Policy applies to all of the services offered by the Website. If you have any queries regarding this Policy please contact us prior to using any of the services.\n" +
            "In this Policy the words and phrases shall have the meaning given in the Terms and Conditions save as when indicated below:\n" +
            "1. User Information\n" +
            "To enable the Website to perform the full range of services the Website may collect the following types of information:\n" +
            "1.1 - User Information. When the User registers with the Website the User submits Registration Details;\n" +
            "1.2 - Cookies. When the User visits the Website the Website sends one or more cookies to the computer being used to access the Website. Cookies are used to enhance the User’s experience and interection with the Website by storing User preferences and tracking User trends. For your information it is possible to disable cookies by adjusting your browser, however it will reduce the services available to you.\n" +
            "1.3 - When the User accesses the Website, the Website servers record information that your browser automatically sends whenever the User visits any website. These may include information such as your Internet Protocol address, browser type, browser language, the date and time of your request and one or more cookies that may uniquely identify your browser.\n" +
            "1.4 - Communications. When any communication, of any form, electronic or non electronic, is sent to the Website, the website may retain these communications in order to keep an accurate history of all correspondence which will help the Website provide the User with a complete and through service.\n" +
            "1.5 - Associated Sites. The Website may from time to time use its services in connection with other web sites. Personal information that is provide to those sites may be sent to the Website in order to deliver the best service possible. Any information gathered in this way is processed in accordance with this Policy.\n" +
            "2. The User's Choice\n" +
            "When a User registers for the service he provides his Registration Details (as defined in the Terms and Conditions) if the Website intends to use the Registration Details in a manner different than the purpose for which it was collected, then the Website will seek the Users prior permission. Such permission maybe declined, in which case the Website may not be able to provide those services to you.\n" +
            "3. Personal Information\n" +
            "The Website will only share personal information with a third party in the following instances:\n" +
            "3.1 - the Website has the Users permission. The Website requires that the User opt-in for the sharing of any sensitive personal information as stated in this Policy at clause 2.\n" +
            "3.2 - The Website have a good faith belief that access, use, preservation or disclosure of such information is reasonably necessary to\n" +
            "3.2.1 - satisfy any applicable law, regulation, legal process or enforceable governmental request, enforce applicable Terms and Conditions, including investigation of potential violations thereof,\n" +
            "3.2.2 - detect, prevent, or otherwise address fraud, security or technical issues, or\n" +
            "3.2.3 - protect against imminent harm to the rights, property or safety of the Website including Users or the public as required or permitted by law.\n" +
            "3.3 - If the Website becomes involved in a merger, acquisition, or any form of sale of some or all of its assets, the Website will provide notice if personal information is to be transferred and subject to a privacy policy different to this Policy.\n" +
            "3.4 - The Website may share with third parties certain pieces of information which does not serve to identify individuals for example; number of Users who searched for a particular area, or how many Users clicked on a advertisement or sponsored link.\n" +
            "4. Advertising\n" +
            "We use third-party advertising companies to serve ads when you visit our website. These companies may use information (not including your name, address, email address, or telephone number) about your visits to this and other websites in order to provide advertisements about goods and services of interest to you. If you would like more information about this practice and to know your choices about not having this information used by these companies, please contact us.\n" +
            "5. Changes to the Policy\n" +
            "The Website may amend the Policy from time to time. If we make any material changes to the Policy, Users will be notified by a prominent message on the website.\n";
    String header="Terms of Service Park in Italia\n";
    String message="Introduction\n" +
            "\n" +
            "Please read these terms and conditions carefully before using ParkItalia and it's mobile apps (\"the Website\") operated by KP Safety Consulting and Training Ltd (\"the Company\").\n" +
            "By registering your details with the Website or using any services provided by the Company (\"the Services\") in any way, you (\"the User\" or \"you\") agree to be bound by the following terms and conditions (\"the Terms and Conditions\").\n" +
            "\n" +
            "You must read and accept all the Terms and Conditions. If you do not accept all the Terms and Conditions you must leave the Website and no longer use or access the Website.\n" +
            "\n" +
            "The Company may amend or terminate the Services from time to time for any reason, without notice, including but not limited to, the right to terminate with or without notice, with no liability to the User any other User or any third party. The Company also maintains the right to amend these Terms and Conditions from time to time without notice. All amendments will be binding upon existing Users therefore Users are advised to review these Terms and Conditions on a regular basis.\n" +
            "\n" +
            "1. Membership Eligibility\n" +
            "Our services are not available to, and may not be used by, temporarily or indefinitely suspended Users. If you do not qualify, please do not use our Services. If you are registering as a business entity, by agreeing to these Terms and Conditions you represent that you have the authority to bind that business entity to these Terms and Conditions.\n" +
            "\n" +
            "2. Registered User Identification\n" +
            "\n" +
            "2.1 - When a User registers with the Website the User will submit their name, unique username, email address and password. The Website will then issue the User with a User identification number, which together with the unique email and password makes up the Registration Details.\n" +
            "\n" +
            "2.2 - It is the responsibility of the User to keep his Registration Details safe and confidential.\n" +
            "\n" +
            "2.3 - The User has no right to disclose his Registration Details to anybody else. Further to this the User accepts that any breach of any third party rights resulting from your prohibited disclosure or negligent safe keeping of Registration Details which then result in any liability of any nature whatsoever shall be the sole liability of the User.\n" +
            "\n" +
            "3. Liability\n" +
            "\n" +
            "3.1 - All Users, whether parking space owners or parking space users hereby release the Website, the Company and its officers, directors, agents, affiliates, parents, subsidiaries, investors and employees from all claims, liability, demands, damages (actual, special, direct, indirect and consequential), losses, costs and expenses, all legal fees, known and unknown, suspected and unsuspected, disclosed and undisclosed, arising out of or in any way connected with any arrangements the User may have made.\n" +
            "\n" +
            "3.2 - The User is wholly responsible for completing all transactions participated in (including but not limited to monitoring the status and complying with all relevant legal obligations). The Website does not, control, endorse, approve or check the availability of the parking space or accuracy of the information provided regarding the parking spaces listed through the Website. You may find other Users' information to be offensive, harmful, inaccurate, or deceptive. Please use caution, common sense, and practice safe trading when using the Website. Please note that there are also risks of dealing with underage persons or people acting under false pretence.\n" +
            "\n" +
            "3.3 - For the avoidance of doubt it is hereby made clear that the Company does not control the information provided by other Users or external websites, which is made available through the Website.\n" +
            "\n" +
            "3.4 - The Company does not accept liability for any loss or damage of any nature arising directly or indirectly as a result of any User acting or refraining from acting as a result of using either the various precedent parking agreements (together \"the Precedent Agreements) provided on the Website from time to time. Users are advised that the Precedent Agreements are to be used as a guide only and are not drafted for any specific purpose or User.\n" +
            "\n" +
            "3.5 – The Company does not accept liability for any loss or damage or fines of any nature arising directly or indirectly as a result of any information found and used on the Website or App.\n" +
            "\n" +
            "4. Information submitted by the User\n" +
            "\n" +
            "4.1 - By submitting any material to the Website, the User automatically grants the Company a royalty free, perpetual, irrevocable, exclusive right and licence to use, reproduce, modify, edit, adapt publish, translate, create derivative works from, distribute, perform and display such material (in whole or part) worldwide and/or to incorporate it in other works in any form, media, or technology, now known or later developed for the full term of any rights that may exist in such content. The User acknowledges that the Company is not obliged to publish any material submitted by the User.\n" +
            "\n" +
            "4.2 - The User must be legally permitted to add any information to the Website.\n" +
            "\n" +
            "4.3 - Without limiting any other remedies available to the Company at law, in equity or under these Terms and Conditions, the Company may, in its sole discretion, suspend or terminate your account if the Company suspect that you have engaged in fraudulent activity in connection with the Website and the service provided. The Company may also use its sole discretion to remove and subsequently ban the car parking information provided by the User.\n" +
            "\n" +
            "5. User Information\n" +
            "\n" +
            "5.1 - \"User Information\" is defined as any information you provide to the Website or other Users in the listing process, in any public message area (including the contact or the feedback area) or through any email feature as run by the Website from time to time. User Information includes the descriptions of car parking facilities and information that are displayed on the Website as well as any content the User may post on the Website. The User is solely responsible for User Information and the Website acts as a passive conveyer of User Information\n" +
            "\n" +
            "5.2 - The User's Information (including but not limited to any car parking space listed) and activities on the Website must not:\n" +
            "\n" +
            "5.2.1 - be false, inaccurate or misleading;\n" +
            "\n" +
            "5.2.2 - be fraudulent in nature;\n" +
            "\n" +
            "5.2.3 - be in breach of any law, statute, ordinance or regulation;\n" +
            "\n" +
            "5.2.4 - be offensive or menacing, abusive, defamatory, trade libelous, unlawfully threatening or unlawfully harassing, obscene or contain child pornography or, if otherwise adult in nature or harmful to persons under the age of 18 years, shall be distributed only to people legally permitted to receive such content.\n" +
            "\n" +
            "5.2.5 - breach copyright, confidentiality, or any other rights, infringe any third party's copyright, patent, trademark, trade secret or other proprietary rights or rights to publicity or privacy;\n" +
            "\n" +
            "5.2.6 - contain any viruses, Trojan horses, worms, time bombs, cancel bots, easter eggs or any other computer programming routines that may damage, modify, delete, detrimentally interfere with, surreptitiously intercept, access without authority or expropriate any system, data or personal information;\n" +
            "\n" +
            "5.2.7 - create liability for the Company or cause us to lose (in whole or in part) the services of our ISPs or other suppliers.\n" +
            "\n" +
            "6 The Users Use of Other Users' Information\n" +
            "\n" +
            "6.1 - The Company does not tolerate spam or unsolicited commercial communications and Users shall not make unsolicited contact to the other Users of the Website.\n" +
            "\n" +
            "7. Breach\n" +
            "\n" +
            "In the event that the User breaches these Terms and Conditions the Company has the right to limit your activity on the Website, immediately issue a warning, suspend or terminate your User registration or any of the User's listed parking spaces and refuse to provide any of the Services to the User without notice to the User for any of the following reasons:\n" +
            "\n" +
            "7.1.1 - the User breaches these Terms and Conditions;\n" +
            "\n" +
            "7.1.2 - if the Company are unable to verify or authenticate the User Information provided to us to our satisfaction;\n" +
            "\n" +
            "7.1.3 - if the Company believe that the User's actions may cause the Website legal liability or financial loss; or\n" +
            "\n" +
            "7.1.4 - if the Company in its sole discretion believes it is in the best interest of the Website and other Users.\n" +
            "\n" +
            "8. Privacy\n" +
            "\n" +
            "The Website has a Privacy Policy which is hereby inferred into these Terms and Conditions. Therefore it is necessary that the User read and agree to the Privacy Policy prior to accepting these Terms and Conditions. Read the Privacy Policy in full by clicking here.\n" +
            "\n" +
            "9. Copyright and Monitoring\n" +
            "\n" +
            "The contents of the Website are protected by international copyright laws and other intellectual property rights. The owner of these rights is in our name, its affiliates or other third party licensors. All product and company names and logos mentioned in our website are the trade marks, service marks or trading names of their respective owners, including us. You may download material from our website for the sole purpose of placing an order with us or using our website as a resource. However, you may not modify, copy, reproduce, republish, upload, post, transmit or distribute, by any means or in any manner, any material or information on or downloaded from our website including but not limited to text, graphics, video, messages, code and/or software without our prior written consent, except where expressly invited to do so, for example in order to complete any test or questionnaire.\n" +
            "\n" +
            "10. No Warranty\n" +
            "\n" +
            "The Company does not guarantee continuous, uninterrupted or secure access to the Website, and the Website may be interfered with factors outside the control of the Company. To the fullest extent permitted by law the Company excludes all implied warranties, conditions and other terms, whether implied or by statute or otherwise, including without limitation any terms as to skill and care or timeliness of performance. This may not apply to Users in certain jurisdictions that do not allow the disclaimer of implied warranties.\n" +
            "\n" +
            "11. Third Party Right\n" +
            "\n" +
            "The Terms and Conditions and the documents referred to in it are made for the benefit of the parties and their successors and permitted assigns and are not intended to benefit, or be enforceable by, anyone else.\n" +
            "\n" +
            "12. Entire Agreement\n" +
            "\n" +
            "These Terms and Conditions and the other documents entered into contemporaneously with or pursuant to them, constitutes the entire agreement and understanding between the parties and supersedes any previous agreement or understanding between the parties with respect to all matters referred to in them.\n" +
            "\n" +
            "13. Governing Law and Jurisdiction\n" +
            "\n" +
            "These Terms and Conditions (together with all documents referred to in them) are governed by and construed in accordance with English law. The parties irrevocably agree that the English courts have non-exclusive jurisdiction to settle any dispute or claim that arises out of or in connection with the Terms and Conditions any documents referred to in them.\n" +
            "\n" +
            "14. No Agency\n" +
            "\n" +
            "Nothing in the Terms and Conditions is intended to, or shall be deemed to, constitute a partnership or joint venture of any kind between any of the parties, nor constitute any party the agent of another party for any purpose. No party shall have authority to act as agent for, or to bind, another party in any way.\n" +
            "\n" +
            "15. Mediation\n" +
            "\n" +
            "If any dispute arises in connection with the Terms and Conditions, the parties will attempt to settle it by mediation as agreed between the Parties. Unless otherwise agreed between the parties, the mediator will be nominated by CEDR.\n" +
            "\n" +
            "16. Fees\n" +
            "\n" +
            "Registering on ParkItalia is free, although you will remain responsible for the costs of accessing and using the Site.\n" +
            "\n" +
            "If you have any questions or queries regarding any of the services or products featured please contact us stayincortona@gmail.com\n";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layoutfragmentinfo);
        TextView textViewaccomodation= (TextView) findViewById(R.id.information_accomodation);
        TextView textViewdinning= (TextView) findViewById(R.id.information_dining);
        TextView textViewservice= (TextView) findViewById(R.id.information_terms_servicesprovide);
        TextView textViewtoptours= (TextView) findViewById(R.id.information_terms_toptours);
        TextView italiansymbols= (TextView) findViewById(R.id.information_terms_roadsign);
        TextView emergencycontacts= (TextView) findViewById(R.id.information_terms_emergencycontact);

        textViewaccomodation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Information.this,InformationDetail.class);
                startActivity(intent);
            }
        });
        textViewdinning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Information.this,InformationDetailsDinning.class);
                startActivity(intent);

            }
        });
        textViewservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Information.this,InformationDetailsServices.class);
                startActivity(intent);

            }
        });
        textViewtoptours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Information.this,InformationDetailTopTours.class);
                startActivity(intent);

            }
        });
        italiansymbols.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Information.this,ItalianRoadApi.class);
                startActivity(intent);

            }
        });
        emergencycontacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Information.this,Emergency.class);
                startActivity(intent);

            }
        });
        findViewById(R.id.information_terms_service).setOnClickListener(this);
        findViewById(R.id.information_privacy_policy).setOnClickListener(this);
        findViewById(R.id.information_what_new).setOnClickListener(this);
        findViewById(R.id.information_share_app).setOnClickListener(this);
        findViewById(R.id.information_rate_app).setOnClickListener(this);
        TextView feedbacks= (TextView) findViewById(R.id.information_send_feedback);
        feedbacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","stayincortona@gmail.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }
        });
        TextView textViewinstagram= (TextView) findViewById(R.id.information_follow_twitter);
        textViewinstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                String facebookUrl = getFacebookPageURL(Information.this);
                facebookIntent.setData(Uri.parse("https://www.instagram.com/stayincortona/"));
                startActivity(facebookIntent);
            }
        });
        TextView textView= (TextView) findViewById(R.id.information_like_facebook);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                String facebookUrl = getFacebookPageURL(Information.this);
                facebookIntent.setData(Uri.parse("https://www.facebook.com/stayincortona"));
                startActivity(facebookIntent);
            }
        });
//https://www.instagram.com/stayincortona/
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.information_terms_service:
//                displayTerms(App.GET_TERMS_SERVICE + SharedPrefrences.getInstance(this).getString(SharedPrefrences.SIGNING_USER_ID));
//                displayTerms(App.GET_TERMS_SERVICE + 1);
                dialog(0);
                break;

            case R.id.information_privacy_policy:
//                displayTerms(App.GET_PRIVACY + SharedPrefrences.getInstance(this).getString(SharedPrefrences.SIGNING_USER_ID));
                dialog(1);
                break;

            case R.id.information_what_new:
//                displayTerms(App.GET_PRIVACY + SharedPrefrences.getInstance(this).getString(SharedPrefrences.SIGNING_USER_ID));
//                displayTerms(App.WHATS_NEW + 1);
                dialog(2);

                break;

            case R.id.information_send_feedback:

                break;

            case R.id.information_share_app:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Hey check out Park Italia app at: https://play.google.com/store/apps/details?id=com.parkitalia.android");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                break;

            case R.id.information_rate_app:
                final String appPackageName = "com.parkitalia.android"; // getPackageName() from Context or Activity object
                try
                {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                }
                catch (android.content.ActivityNotFoundException anfe)
                {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }
                break;

            case R.id.information_like_facebook:

                break;

            case R.id.information_follow_twitter:

                break;

        }
    }

    public static String FACEBOOK_URL = "https://www.facebook.com/YourPageName";

    public static String FACEBOOK_PAGE_ID = "YourPageName";

    //method to get the right URL to use in the intent
    public String getFacebookPageURL(Context context)
    {
        PackageManager packageManager = context.getPackageManager();
        try
        {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850)
            { //newer versions of fb app
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            }
            else
            { //older versions of fb app
                return "fb://page/" + FACEBOOK_PAGE_ID;
            }
        }
        catch (PackageManager.NameNotFoundException e)
        {
            return FACEBOOK_URL; //normal web url
        }
    }
public void dialog(int a){
    if(a==0){
//        showProgressing(this, String.valueOf(getText(R.string.Loader)));
        new AlertDialog.Builder(Information.this)
                .setTitle(header)
                .setMessage(message)

//                                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener()
//                                        {
//                                            public void onClick(DialogInterface dialog, int which)
//                                            {
//                                                // do nothing
//                                            }
//                                        })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
    if(a==1){
//        showProgressing(this, String.valueOf(getText(R.string.Loader)));
        new AlertDialog.Builder(Information.this)
                .setTitle(policyHeader)
                .setMessage(policydetail)

//                                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener()
//                                        {
//                                            public void onClick(DialogInterface dialog, int which)
//                                            {
//                                                // do nothing
//                                            }
//                                        })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
    if(a==2){
//        showProgressing(this, String.valueOf(getText(R.string.Loader)));
        new AlertDialog.Builder(Information.this)
                .setTitle(licence)
                .setMessage(licencedetail)


//                                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener()
//                                        {
//                                            public void onClick(DialogInterface dialog, int which)
//                                            {
//                                                // do nothing
//                                            }
//                                        })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }


}
    private void displayTerms(String url)
    {
        showProgressing(this, String.valueOf(getText(R.string.Loader)));
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest jsObjRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        hideProgressing();
                        try
                        {
                            JSONObject json = new JSONObject(response);

                            Log.e("seema", "ghhhh" + json);

                            if (json.getString("success").equals("1"))
                            {
                                String description = json.getJSONObject("about").getString("description");
                                String title = json.getJSONObject("about").getString("title");
                                new AlertDialog.Builder(Information.this)
                                        .setTitle(title)
                                        .setMessage(description)
                                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener()
                                        {
                                            public void onClick(DialogInterface dialog, int which)
                                            {
                                                // continue with delete
                                            }
                                        })
//                                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener()
//                                        {
//                                            public void onClick(DialogInterface dialog, int which)
//                                            {
//                                                // do nothing
//                                            }
//                                        })
                                        .setIcon(android.R.drawable.ic_dialog_alert)
                                        .show();
                            }
                            else
                            {
                                Util.Toast(Information.this, json.getString("success"));
                                hideProgressing();
                            }

                        }
                        catch (JSONException e1)
                        {
                            Util.Toast(Information.this, "Internal server error");
                            e1.printStackTrace();
                            hideProgressing();
                        }

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        hideProgressing();
                        Util.Toast(Information.this, "Internal server error");
                    }
                }
        );
        queue.add(jsObjRequest);
    }
}
