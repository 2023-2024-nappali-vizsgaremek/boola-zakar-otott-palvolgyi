package com.boola.models

import java.util.UUID

data class Profile(val id:UUID, val name:String, val isBusiness:Boolean, val languageId:String, val accountId:UUID,val accountEmail:String);
