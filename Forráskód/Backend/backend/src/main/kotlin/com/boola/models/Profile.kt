package com.boola.models

import java.util.UUID

data class Profile(val id:Long, val name:String, val isBusiness:Boolean, val languageId:String, val accountId:UUID,val accountEmail:String);
