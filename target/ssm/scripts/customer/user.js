/**
 *
 */

Ext.define('User', {
    extend:'Ext.data.Model',
    idProperty:'id',
    fields:[
        {name:'id'},
        {name:'name'},
        {name:'age'}
    ]
});