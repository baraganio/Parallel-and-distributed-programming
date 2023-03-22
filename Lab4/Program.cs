using lab4.impl;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace lab4
{
    class Program
    {


        static void Main(string[] args)
        {
            var hosts = new string[] { "www.cs.ubbcluj.ro/~rlupsa/edu/pdp/", "www.stackoverflow.com/", "www.cs.ubbcluj.ro/~motogna/LFTC" }.ToList();
            TaskImpl.run(hosts, false);
            
            while (true)
            {

            }
       
        }
    }
}